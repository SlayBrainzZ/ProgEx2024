USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_ExceptionMaster_GetException]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_ExceptionMaster_GetException] 

(
@Exception_Details varchar(500),
@Form varchar(200),
@Method varchar(200)
)

AS

BEGIN

INSERT INTO [ExceptionMaster]([Exception_Date], [Exception_Date], [FormName], [MethodName])
VALUES						(@Exception_Details, GETDATE(), @Form, @Method )



END
GO
