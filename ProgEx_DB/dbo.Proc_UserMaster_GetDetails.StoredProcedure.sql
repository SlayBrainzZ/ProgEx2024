USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_UserMaster_GetDetails]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_UserMaster_GetDetails] --'Aryan Kumar'

(
@Uname varchar(500)

)
AS
BEGIN
SELECT [Name], [Email_ID], [Phone_Number], [Address] 
FROM [UserMaster] 
WHERE [Username] = @Uname

END
GO
