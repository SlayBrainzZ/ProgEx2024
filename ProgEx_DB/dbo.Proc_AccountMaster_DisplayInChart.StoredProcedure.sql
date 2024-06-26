USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_DisplayInChart]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_AccountMaster_DisplayInChart]

AS
BEGIN
DECLARE @RowCount int

SELECT  [City], SUM([Balance_Amount]) 'Balance_Amount' 
FROM [AccountMaster] 
WHERE [AccountID] IN (SELECT [AccountID] 
						FROM [SalesMaster])
GROUP BY  [City]

END
GO
