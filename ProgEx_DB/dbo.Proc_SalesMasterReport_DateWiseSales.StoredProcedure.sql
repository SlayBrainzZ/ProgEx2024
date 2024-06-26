USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMasterReport_DateWiseSales]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMasterReport_DateWiseSales] --'2021-07-13', '2021-12-11'

(
@StartDate varchar(100),
@EndDate varchar(100)
)

AS
BEGIN
DECLARE @RowCount int

SELECT @RowCount = COUNT(Amount) FROM [SalesMaster]

SELECT [Amount], [TransactionDate], @RowCount 'RowCount' 
FROM [SalesMaster]
WHERE [TransactionDate] BETWEEN @StartDate AND @EndDate

END
GO
