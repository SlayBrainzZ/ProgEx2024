USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_GetDate]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_GetDate]

AS
BEGIN
DECLARE @RowCount int

SELECT @RowCount = COUNT([TransactionDate]) 
FROM [SalesMaster]

SELECT CONVERT(varchar, [TransactionDate], 6) 'Date' , @RowCount 'RowCount' 
FROM [SalesMaster] 
ORDER BY [TransactionDate] ASC


END
GO
