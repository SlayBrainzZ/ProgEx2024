USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_DeleteRow]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_SalesMaster_DeleteRow]

(@ID int)

AS
BEGIN
DECLARE @AccID int

SELECT @AccID = [AccountID] 
FROM [SalesMaster]
WHERE [SalesID] = @ID

DELETE FROM [AccountMaster]
WHERE [AccountID] = @AccID

DELETE FROM [SalesMaster]
WHERE [SalesID] = @ID

DELETE FROM [TransactionMaster]
WHERE [SalesID] = @ID


END
GO
