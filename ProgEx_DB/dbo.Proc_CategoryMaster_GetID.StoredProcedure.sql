USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_CategoryMaster_GetID]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_CategoryMaster_GetID]

(@CatName varchar(100))

AS
BEGIN

SELECT [CategoryID] 
FROM [CategoryMaster] 
WHERE [CategoryName] = @CatName
END
GO
