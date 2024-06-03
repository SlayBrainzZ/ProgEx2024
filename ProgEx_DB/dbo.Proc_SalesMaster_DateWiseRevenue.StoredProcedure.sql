USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_DateWiseRevenue]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_DateWiseRevenue] --'2021-11-11', '2021-12-11'

(
@DateFrom varchar(100),
@DateTo varchar(100)
)
--SELECT * FROM SalesMaster
AS
BEGIN


SELECT SUM([Amount]) 'Amt' 
FROM [SalesMaster] 
WHERE [TransactionDate] BETWEEN @DateFrom AND @DateTo


END

GO
