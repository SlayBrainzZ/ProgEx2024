USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_CategoryMaster_CategoryCityReport]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_CategoryMaster_CategoryCityReport] --'Dinner Set'

(
@Ctg varchar(100)
)

AS
BEGIN

DECLARE @CtgCount int
DECLARE @CtgID int
DECLARE @SalesID int
DECLARE @AccID int

SELECT @CtgID = [CategoryID]
FROM [CategoryMaster] 
WHERE [CategoryName] = @Ctg


SELECT im.[Item_Name], SUM(tm.[Quantity]) 'TotalQtty', am.[City] 
FROM [TransactionMaster] tm
JOIN [ItemMaster] im
ON tm.[Item_ID] = im.[Item_ID]
JOIN [SalesMaster] sm 
ON tm.[SalesID] = sm.[SalesID]
JOIN [AccountMaster] am
ON sm.[AccountID] = am.[AccountID]
WHERE im.[CategoryID] = @CtgID
GROUP BY im.[Item_Name], am.[City]

END
GO
