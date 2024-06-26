USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_TransactionMaster_Update]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_TransactionMaster_Update] 

(@Choice int,
@TranID int,
@Qtty int,
@Rate int,
@Date date)

AS
DECLARE @SalesID int
DECLARE @Amt decimal(15,2)
DECLARE @AccID int
BEGIN

SELECT @SalesID = [SalesID] 
FROM [TransactionMaster]
WHERE [TransactionID] = @TranID


SELECT @AccID = [AccountID] 
FROM [SalesMaster]
WHERE [SalesID] = @SalesID

IF @Choice=1

	BEGIN
	UPDATE [TransactionMaster]
	SET [Quantity] = @Qtty
	WHERE [TransactionID] = @TranID

	SELECT @Amt = SUM([Quantity] * [Rate])
	FROM [TransactionMaster]
	WHERE [SalesID] = @SalesID

	UPDATE [SalesMaster]
	SET [Amount] = @Amt
	WHERE [SalesID] = @SalesID

	UPDATE [AccountMaster]
	SET [Balance_Amount] = @Amt
	WHERE [AccountID] = @AccID

	END
IF @Choice=2
	BEGIN
	
	UPDATE [TransactionMaster]
	SET [Rate] = @Rate
	WHERE [TransactionID] = @TranID

	SELECT @Amt = SUM([Quantity] * [Rate])
	FROM [TransactionMaster]
	WHERE [SalesID] = @SalesID

	UPDATE [SalesMaster]
	SET [Amount] = @Amt
	WHERE [SalesID] = @SalesID

	UPDATE [AccountMaster]
	SET [Balance_Amount] = @Amt
	WHERE [AccountID] = @AccID

	END

IF @Choice =3
	BEGIN
	UPDATE [TransactionMaster]
	SET [Date] = @Date
	WHERE [TransactionID] = @TranID

	END

END
GO
