USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_GetName]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_AccountMaster_GetName] 

(@ID int)

AS
BEGIN

IF EXISTS(SELECT NULL 
			FROM [AccountMaster] 
			WHERE [AccountID] = @ID)

		SELECT [NAME] 'output' 
		FROM [AccountMaster] 
		WHERE [AccountID] = @ID
ELSE 
		SELECT '0' 'output'

END
GO
