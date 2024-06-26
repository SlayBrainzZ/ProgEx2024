USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_GetInvoiceDetails]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_GetInvoiceDetails] 

(
@SalesID int
)

AS
BEGIN

DECLARE @TranID int

SELECT @TranID = [TransactionID] 
FROM [TransactionMaster] 
WHERE [SalesID] = @SalesID

SELECT tm.[Rate], tm.[Quantity], sm.[Amount], im.[Item_Name] 
FROM [TransactionMaster] tm
JOIN [SalesMaster] sm 
ON tm.[SalesID] = sm.[SalesID]
JOIN [ItemMaster] im 
ON tm.[Item_ID] = im.[Item_ID]
WHERE tm.[TransactionID] = @TranID

END
GO
