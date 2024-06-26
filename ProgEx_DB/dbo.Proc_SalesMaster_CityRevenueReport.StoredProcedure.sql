USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_CityRevenueReport] 
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_SalesMaster_CityRevenueReport] --'Bangalore'
(
@City varchar(100)
)

AS
BEGIN

DECLARE @AccID int

SELECT SUM(sm.[Amount]) 'Amt' 
FROM [SalesMaster] sm 
JOIN [AccountMaster] am
ON am.[AccountID] = sm.[AccountID]
WHERE am.[City] = @City

END
GO
