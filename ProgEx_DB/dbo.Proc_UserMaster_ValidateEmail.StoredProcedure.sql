USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_UserMaster_ValidateEmail]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_UserMaster_ValidateEmail] 
(

@Email varchar(100)
)
AS

BEGIN

IF EXISTS(SELECT NULL 
			FROM [UserMaster] 
			WHERE [Email_ID] = @Email)
	BEGIN
		
		SELECT [Password] 
		FROM [UserMaster] 
		WHERE [Email_ID] = @Email
	END
ELSE
	BEGIN
		SELECT '-1' 'Password'
	END
END
GO
