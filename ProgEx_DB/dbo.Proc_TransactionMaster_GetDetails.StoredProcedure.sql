USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_TransactionMaster_GetDetails] 
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_TransactionMaster_GetDetails] 
(
@SalesID int
)
AS
BEGIN

DECLARE @RowCount int

SELECT @RowCount= COUNT([TransactionID])
FROM [TransactionMaster] 
WHERE [SalesID] = @SalesID

SELECT [TransactionID], CONVERT(varchar(15), Date, 106) 'Date', [Rate], [Quantity], im.[Item_Name], @RowCount 'RowCount' 
FROM [TransactionMaster] tm
JOIN [ItemMaster] im
ON tm.[Item_ID] = im.[Item_ID]
WHERE tm.[SalesID] = @SalesID

END
GO
