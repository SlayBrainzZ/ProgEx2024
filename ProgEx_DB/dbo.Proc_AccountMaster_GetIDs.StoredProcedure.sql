USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_GetIDs]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_AccountMaster_GetIDs]

AS
BEGIN

DECLARE @RowCount int

SELECT @RowCount= COUNT(AccountID)
FROM AccountMaster

SELECT [AccountID], @RowCount 'RowCount' 
FROM [AccountMaster]

END
GO
