USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_Delete]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_Delete]  --'Devansh'

(@CusName varchar(100))

AS
BEGIN

DECLARE @AccID int
DECLARE @SalesID int
DECLARE @Amt decimal(10,2)

SELECT @AccID = [AccountID]
FROM [AccountMaster] 
WHERE [Name] = @CusName

SELECT @SalesID = [SalesID]
FROM [SalesMaster] 
WHERE [AccountID] = @AccID

SELECT @Amt = [Amount]
FROM [SalesMaster] 
WHERE [AccountID] = @AccID


DELETE FROM [TransactionMaster]
WHERE [SalesID] = @SalesID

DELETE FROM [SalesMaster]
WHERE [SalesID] = @SalesID

UPDATE [AccountMaster]
SET [Balance_Amount] = [Balance_Amount] - @Amt
WHERE [AccountID] = @AccID
	
END
GO
