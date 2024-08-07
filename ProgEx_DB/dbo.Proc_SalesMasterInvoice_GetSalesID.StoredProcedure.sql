USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMasterInvoice_GetSalesID]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMasterInvoice_GetSalesID] 

(@AccID int)

AS
BEGIN

DECLARE @SalesID int

SELECT @SalesID = [SalesID]
FROM [SalesMaster]
WHERE [AccountID] = @AccID

SELECT [Address1], [Address2], [Address3], @SalesID 'SalesID' 
FROM [AccountMaster]
WHERE [AccountID] = @AccID
END
GO
