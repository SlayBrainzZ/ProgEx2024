USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_GetData]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_GetData]

(@CusName varchar(100))
AS
BEGIN
DECLARE @CusID int
SELECT @CusID = [AccountID] 
FROM [AccountMaster] 
WHERE [Name] = @CusName

SELECT am.[Balance_Amount], sm.[TransactionDate]
FROM [AccountMaster] am
INNER JOIN [SalesMaster] sm
ON am.[AccountID] = sm.[AccountID]
WHERE am.[AccountID] = @CusID


END
GO
