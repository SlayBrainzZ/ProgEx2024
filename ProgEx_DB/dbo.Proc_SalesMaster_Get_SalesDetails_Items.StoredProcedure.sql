USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_Get_SalesDetails_Items]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_Get_SalesDetails_Items] 

(@SalesID int) 

AS
BEGIN
DECLARE @ItemID int 
DECLARE @ItemName varchar(100)

SELECT @ItemID = [Item_ID] 
FROM [TransactionMaster] 
WHERE [SalesID] = @SalesID

SELECT @ItemName = [Item_Name] 
FROM [ItemMaster] 
WHERE [Item_ID] = @ItemID


SELECT @ItemName 'Item', [Amount], [TransactionDate] 
FROM [SalesMaster] 
WHERE [SalesID] = @SalesID




END
GO
