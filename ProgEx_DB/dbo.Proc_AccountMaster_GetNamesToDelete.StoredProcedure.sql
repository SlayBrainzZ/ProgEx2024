USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_GetNamesToDelete]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_AccountMaster_GetNamesToDelete]

AS

BEGIN
DECLARE @RowCount int
SELECT @RowCount = COUNT([Name]) 
FROM [AccountMaster] 
WHERE [AccountID] NOT IN (SELECT [AccountID] 
							FROM [AccountMaster])

SELECT @RowCount 'RowCount', [Name] 
FROM [AccountMaster] 
WHERE [AccountID] NOT IN (SELECT [AccountID] 
							FROM [SalesMaster])

END
GO
