USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_UserMaster_Read]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_UserMaster_Read]

AS
BEGIN

SELECT [UserID], [Name], [Username], [Address], [Phone_Number], [Email_ID]
FROM [UserMaster]

END

GO
