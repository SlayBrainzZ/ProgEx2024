USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_UserMaster_ChangePassword]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE Proc [dbo].[Proc_UserMaster_ChangePassword] 
(
@Uname varchar(50),
@Pass varchar(50),
@NewPass varchar(50)=NULL

)
AS
BEGIN

IF EXISTS(SELECT NULL 
			FROM [UserMaster] 
			WHERE [Password]= @Pass AND [Name]=@Uname)
	BEGIN
		UPDATE [UserMaster]
		SET Password = @NewPass
		WHERE Name = @Uname
		SELECT '1' 'output'
		
	END
ELSE
	BEGIN
		SELECT '0' 'output'
	END

END

GO
