USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_CategoryMaster_GetCategories]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_CategoryMaster_GetCategories]

AS
BEGIN

SELECT [CategoryID], [CategoryName]  
FROM [CategoryMaster]

END
GO
