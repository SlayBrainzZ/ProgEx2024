USE Prog_Ex
GO
--Object:  StoredProcedure [dbo].[Proc_DayMaster_Get]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Proc_DayMaster_Get]
(
@DayID int
)
AS
BEGIN

SELECT [DAY] 
FROM [DayMaster]
WHERE [Day_ID] = @DayID

END
GO
