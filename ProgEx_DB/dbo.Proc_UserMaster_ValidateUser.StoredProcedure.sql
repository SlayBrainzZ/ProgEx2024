USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_UserMaster_ValidateUser]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_UserMaster_ValidateUser] 

(
@Name varchar(50),
@Pass varchar(50)
)
AS
BEGIN
IF EXISTS(SELECT NULL 
			FROM [UserMaster]
			WHERE [Username] = @Name 
			AND [Password] = @Pass)
	BEGIN	
		SELECT 1 'Out'
	END
ELSE 
	BEGIN
		SELECT 0 'Out'
	END

END
GO
