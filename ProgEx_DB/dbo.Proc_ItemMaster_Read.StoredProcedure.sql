USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_ItemMaster_Read]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROC [dbo].[Proc_ItemMaster_Read]

AS
BEGIN

SELECT [Item_ID], [Item_Name], [SKU_Code], [Description], [CategoryName]
FROM [ItemMaster] im 
INNER JOIN [CategoryMaster] cm
ON im.[CategoryID] = cm.[CategoryID] 
ORDER BY [Item_ID] DESC

END



GO
