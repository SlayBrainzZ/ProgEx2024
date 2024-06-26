USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_CategoryMaster_Read]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_CategoryMaster_Read]

AS
BEGIN
DECLARE @RowCount int
SELECT @RowCount = COUNT([CategoryName]) 
FROM [CategoryMaster]

SELECT [CategoryID], [CategoryName], @RowCount 'RowCount' 
FROM [CategoryMaster] 
ORDER BY [CategoryID] ASC

END
GO
