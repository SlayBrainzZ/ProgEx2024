USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_CategoryMaster_GetCategory]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_CategoryMaster_GetCategory] 

AS
BEGIN
DECLARE @RowCount int

SELECT @RowCount = COUNT([CategoryName]) 
FROM [CategoryMaster] 
WHERE [CategoryID] NOT IN (SELECT [CategoryID] 
							 FROM [ItemMaster])

SELECT [CategoryName], @RowCount 'RowCount' 
FROM [CategoryMaster] 
WHERE [CategoryID] NOT IN (SELECT [CategoryID] 
							FROM [ItemMaster])

END

GO
