USE [Prog_Ex]
GO
DROP PROCEDURE IF EXISTS [dbo].[Proc_SalesMaster_MonthlyRevReport]
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_MonthlyRevReport]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_MonthlyRevReport] --'December'

( @Date varchar(100)
)

AS
BEGIN
DECLARE @Month datetime
DECLARE @TotalAmt decimal(10,2)


SELECT @Month = [Month_ID]
FROM [MonthMaster]
WHERE [MonthName] = @Date


SELECT SUM([Amount]) 'Amt' 
FROM [SalesMaster] 
WHERE DATEPART(MONTH, [TransactionDate]) = @Month

END
GO
