USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_ItemMaster_GetData]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_ItemMaster_GetData] 

(@ItemName varchar(100))

AS

BEGIN

SELECT [SKU_Code], [Description]
FROM [ItemMaster] 
WHERE [Item_Name] = @ItemName

		 

END
GO
