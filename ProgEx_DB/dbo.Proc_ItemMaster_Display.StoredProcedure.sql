USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_ItemMaster_Display]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_ItemMaster_Display]

AS
BEGIN
DECLARE @RowCount int

SELECT @RowCount = COUNT([Item_Name]) 
FROM [ItemMaster]

SELECT [Item_Name], @RowCount 'RowCount' 
FROM [ItemMaster] 

END


GO
