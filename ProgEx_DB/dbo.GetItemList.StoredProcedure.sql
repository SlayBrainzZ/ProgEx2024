USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[GetItemList]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE Procedure [dbo].[GetItemList] 
(@ItemID int)
AS 
BEGIN 
SELECT [Item_ID] 'Item Code', [SKU_Code] 'SKU Code', [Item_Name] 'Item Name ', [Description] 
FROM  [ItemMaster]
WHERE [Item_ID] = @ItemID

END
GO
