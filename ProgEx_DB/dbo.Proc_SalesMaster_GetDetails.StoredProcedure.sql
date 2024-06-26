USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_GetDetails]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_SalesMaster_GetDetails]
--Get amount and Date 
@CusName varchar(100)
AS
BEGIN
DECLARE @AccID int

SELECT @AccID = [AccountID] 
FROM [AccountMaster]
WHERE [Name] = @CusName

SELECT [Amount], [TransactionDate] 
FROM [SalesMaster] 
WHERE [AccountID] = @AccID

END
GO
