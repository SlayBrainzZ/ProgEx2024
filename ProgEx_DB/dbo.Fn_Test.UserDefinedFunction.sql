USE [Prog_Ex]
GO
Drop Function if Exists [dbo].[Fn_Test];
--Object:  UserDefinedFunction [dbo].[Fn_Test]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE Function [dbo].[Fn_Test]
(
@Id int
)
returns varchar(50)
As
Begin
Declare
@Days varchar(50)

SELECT @Days = [Day]
FROM [DayMaster]
WHERE [Day_ID] = @ID

return @Days

End


GO
