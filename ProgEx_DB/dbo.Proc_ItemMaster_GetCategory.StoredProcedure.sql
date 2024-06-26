USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_ItemMaster_GetCategory]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_ItemMaster_GetCategory]

AS
BEGIN
DECLARE @RowCount int

SELECT @RowCount = COUNT([CategoryName])
FROM [CategoryMaster]

SELECT [CategoryName], @RowCount 'RowCount' 
FROM [CategoryMaster]

END
GO
