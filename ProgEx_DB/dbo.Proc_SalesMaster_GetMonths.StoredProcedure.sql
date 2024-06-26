USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_GetMonths]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_GetMonths]

AS
BEGIN

DECLARE @RowCount int

SELECT @RowCount = COUNT(DISTINCT DATENAME(MONTH, [TransactionDate]))  
FROM [SalesMaster]

SELECT DISTINCT DATENAME(MONTH, [TransactionDate]) 'Months', DATEPART(MONTH, [TransactionDate]), @RowCount 'RowCount' 
FROM [SalesMaster]  
ORDER BY DATEPART(MONTH, [TransactionDate]) ASC

END
GO
