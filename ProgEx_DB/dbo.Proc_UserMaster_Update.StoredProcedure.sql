USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_UserMaster_Update]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_UserMaster_Update] --'dv', ''

(
@Username varchar(100),
@NewUser varchar(100),
@Name varchar(50),
@Email varchar(100),
@Phone varchar(20),
@Address varchar(100),
@Pass varchar(100)
)
AS
BEGIN

IF EXISTS (SELECT NULL 
			FROM [UserMaster] 
			WHERE [Password]= @Pass)
	BEGIN
		UPDATE [UserMaster]
		SET [Username] = @NewUser,
			[Name]= @Name,
			[Email_ID] = @Email,
			[Phone_Number] = @Phone,
			[Address]= @Address
		WHERE [Username] = @Username 
		
		SELECT '1' 'Output'
	END
ELSE
	BEGIN
		SELECT '0' 'Output'
	END

END
GO
