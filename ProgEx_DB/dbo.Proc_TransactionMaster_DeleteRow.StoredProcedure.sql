USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_TransactionMaster_DeleteRow]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- Delete rows from transaction, account & sales master using tran ID --
CREATE PROC [dbo].[Proc_TransactionMaster_DeleteRow] 
(@TranID int)

AS

BEGIN
DECLARE @Count int
DECLARE @SalesID int
DECLARE @AccID int
DECLARE @Amount decimal(16,2)

-- Calc amount to be subtracted --
SELECT @Amount = [Rate] * [Quantity]
FROM [TransactionMaster]
WHERE [TransactionID] = @TranID

-- Get SalesID corresponding to TranID --
SELECT @SalesID = [SalesID]
FROM  [TransactionMaster]
WHERE [TransactionID] = @TranID

-- Get AccID corresponding to SalesID --
SELECT @AccID = [AccountID]
FROM [SalesMaster]
WHERE [SalesID] = @SalesID

-- Get number of Transactions -- 
SELECT @Count = COUNT(*) 
FROM [TransactionMaster]
WHERE [SalesID] = @SalesID

PRINT @Count
IF @Count>1
	
	DELETE FROM [TransactionMaster]
	WHERE [TransactionID] = @TranID

	UPDATE [SalesMaster]
	SET [Amount] = [Amount] - @Amount
	WHERE [SalesID] = @SalesID
	
	UPDATE [AccountMaster]
	SET [Balance_Amount] = [Balance_Amount] - @Amount
	WHERE [AccountID] = @AccID

IF @Count=1
	SELECT 0
	

END
GO
