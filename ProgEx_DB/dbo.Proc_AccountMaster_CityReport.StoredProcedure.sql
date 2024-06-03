USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_CityReport]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_AccountMaster_CityReport]

(
@City varchar(100) 
)

AS
BEGIN

SELECT [Name]
FROM [AccountMaster]
WHERE [City] = @City


END
GO
