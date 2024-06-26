USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_GetNames]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_GetNames]

AS

BEGIN
DECLARE @RowCount int

SELECT @RowCount = COUNT([Name]) 
FROM [AccountMaster]
WHERE [AccountID] IN (SELECT [AccountID] 
						FROM [AccountMaster])

SELECT @RowCount 'RowCount', [Name] 
FROM [AccountMaster]
WHERE [AccountID] IN (SELECT [AccountID] 
						FROM [SalesMaster])

END
GO
