USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_MonthlyRevenue_Chart]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_MonthlyRevenue_Chart]

AS
BEGIN

SELECT TOP 12 DATENAME(MONTH, [TransactionDate]) 'Month', SUM([Amount]) 'Amount' 
FROM [SalesMaster] 
WHERE DATEPART(YEAR, [TransactionDate])= DATEPART(YEAR, GetDate()) 
GROUP BY DATENAME(MONTH, [TransactionDate]) 

END
GO
