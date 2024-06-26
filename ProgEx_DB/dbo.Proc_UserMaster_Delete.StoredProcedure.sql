USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_UserMaster_Delete]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_UserMaster_Delete]

(
@Pwd varchar(100)
)

AS
BEGIN

IF EXISTS (SELECT NULL 
			FROM [UserMaster]
			WHERE [Password]=@Pwd)
	BEGIN
		DELETE FROM [UserMaster]
		WHERE [Password] = @Pwd

		SELECT '1' 'out'
	END
ELSE
	BEGIN
		SELECT '0' 'out'
	END

END
GO
