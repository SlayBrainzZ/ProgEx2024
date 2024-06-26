USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_GetLastAccID]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_GetLastAccID] 
(
@Amt decimal(16,2)
)

AS
BEGIN

DECLARE 
@AccID int
SELECT @AccID = MAX([AccountID]) 
FROM AccountMaster

INSERT INTO [SalesMaster]([TransactionDate], [Amount], [AccountID])
VALUES					(GETDATE(), @Amt, @AccID)

END
GO
