USE [Prog_Ex]
GO
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

SELECT @Days = [Weekdays]
FROM [DayMaster]
WHERE ID = @ID

return @Days

End


GO
