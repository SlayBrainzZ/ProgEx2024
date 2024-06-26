USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_InsertAmount]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_AccountMaster_InsertAmount] 

(
@BalanceAmt decimal(16,2),
@TranDate datetime
)

AS

BEGIN
DECLARE 
@AccID int,
@SalesID int

SELECT @AccID = MAX([AccountID]) 
FROM [AccountMaster]

SELECT @SalesID = MAX([SalesID]) 
FROM [SalesMaster]

UPDATE  [AccountMaster] 
SET [Balance_Amount] = @BalanceAmt
WHERE [AccountID] = @AccID

UPDATE [SalesMaster]
SET [Amount] = @BalanceAmt, 
	[TransactionDate] = @TranDate
WHERE SalesID = @SalesID

END
GO
