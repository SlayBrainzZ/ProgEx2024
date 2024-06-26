USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_Details]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_SalesMaster_Details]

AS
BEGIN

SELECT sm.[SalesID],  am.[Name], [Amount],[TransactionDate], im.[Item_Name], tm.[Quantity], tm.[Rate] 
FROM [SalesMaster] sm 
JOIN [AccountMaster] am
ON sm.[AccountID] = am.[AccountID]
JOIN [TransactionMaster] tm
ON sm.[SalesID] = tm.[SalesID]
JOIN [ItemMaster] im
ON tm.[Item_ID] = im.[Item_ID]
ORDER BY [SalesID] DESC

END
GO
