USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_Sales_Read]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_Sales_Read]

AS

BEGIN

SELECT am.[Name], im.[Item_Name], tm.[Quantity], tm.[Rate], sm.[Amount], sm.[TransactionDate]  
FROM [SalesMaster] sm
JOIN [TransactionMaster] tm
ON sm.[SalesID] = tm.[SalesID]
JOIN [AccountMaster] am
ON sm.[AccountID] = am.[AccountID]
JOIN [ItemMaster] im
ON tm.[Item_ID] = im.[Item_ID]

END
GO
