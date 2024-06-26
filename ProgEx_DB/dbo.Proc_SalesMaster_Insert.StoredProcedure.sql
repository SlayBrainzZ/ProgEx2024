USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_Insert]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_Insert] 

(
@CusName varchar(100),
@Qtty int,
@Rate decimal(10,2),
@Date date,
@Item varchar(100)

)

AS

BEGIN
DECLARE @SalesID int
DECLARE @CusID int
DECLARE @ItemID int

SELECT @CusID = [AccountID] 
FROM [AccountMaster] 
WHERE [Name] = @CusName

SELECT @ItemID = [Item_ID]
FROM [ItemMaster] 
WHERE [Item_Name] = @Item

INSERT INTO [SalesMaster] ([TransactionDate], [Amount], [AccountID])
					VALUES (@Date, (@Qtty * @Rate), @CusID)

SELECT @SalesID = @@IDENTITY

	


INSERT INTO [TransactionMaster] ([Quantity], [Rate], [SalesID], [Date], [Item_ID])
							VALUES (@Qtty, @Rate, @SalesID, @Date, @ItemID)

UPDATE [AccountMaster]
SET [Balance_Amount] = IsNull([Balance_Amount],0) + (@Qtty*@Rate)
WHERE [AccountID] = @CusID


END
GO
