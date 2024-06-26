USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_userMaster_CreateNewuser]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_userMaster_CreateNewuser]
(
@Name varchar(50),
@Email varchar(100),
@Phone varchar(20),
@Address varchar(200),
@Username varchar(100),
@Password varchar(100)
)
AS
BEGIN
IF NOT EXISTS(SELECT NULL 
				FROM [UserMaster] 
				WHERE [Username] = @Username OR Email_ID= @Email OR Phone_Number = @Phone )
	BEGIN
		INSERT INTO [UserMaster]([Name], [Email_ID], [Phone_Number], [Address], [Username], [Password])
					VALUES	(@Name, @Email, @Phone, @Address, @Username, @Password)
		SELECT '1' 'output'
	END
ELSE
	BEGIN
		SELECT '0' 'output'
	END
END



GO
