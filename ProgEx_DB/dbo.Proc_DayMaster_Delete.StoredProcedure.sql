USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_DayMaster_Delete]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Proc_DayMaster_Delete]
(
@DayID int
)
AS
BEGIN

DELETE FROM [DayMaster] 
WHERE [Day_ID] = @DayID

END
GO
