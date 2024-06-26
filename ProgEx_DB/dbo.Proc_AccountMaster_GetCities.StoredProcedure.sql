USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_GetCities]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_AccountMaster_GetCities]

AS
BEGIN

DECLARE @RowCount int
SELECT  @RowCount = COUNT(DISTINCT City) FROM AccountMaster

SELECT DISTINCT [City] 'City', @RowCount 'RowCount' 
FROM [AccountMaster]

END


GO
