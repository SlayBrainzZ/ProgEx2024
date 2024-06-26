USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMasterInvoice_GetSalesID_Address]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMasterInvoice_GetSalesID_Address] 

(@AccID int)

AS
BEGIN

DECLARE @SalesID int
DECLARE @RowCount int

SELECT @RowCount= COUNT([SalesID]) 
FROM [SalesMaster] 
WHERE [AccountID] = @AccID

SELECT sm.[SalesID], am.[Name], am.[Address1], am.[Address2], am.[Address3], CONVERT(varchar(10), GETDATE(), 106) 'Date' , @RowCount 'RowCount' 
FROM [SalesMaster] sm
JOIN [AccountMaster] am
ON am.[AccountID] = sm.[AccountID]
WHERE sm.[AccountID] = @AccID

END
GO
