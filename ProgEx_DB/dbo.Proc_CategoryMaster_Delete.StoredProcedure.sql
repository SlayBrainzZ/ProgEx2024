USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_CategoryMaster_Delete]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_CategoryMaster_Delete]
(@CatName varchar(100))
AS

BEGIN

IF EXISTS( SELECT NULL 
			FROM [CategoryMaster] 
			WHERE [CategoryName] = @CatName)
	BEGIN
		DELETE FROM [CategoryMaster] 
		WHERE [CategoryName] = @CatName

		SELECT '1' 'output'
	END

ELSE
	BEGIN
		SELECT '0' 'output'
	END

END
GO
