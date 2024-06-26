USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_ItemMaster_Update]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_ItemMaster_Update] 
(
@OldName varchar(100),
@ItemName varchar(100),
@SKU varchar(50),
@Desc varchar(300)
)
AS
BEGIN 
DECLARE @ID int

SELECT @ID = [Item_ID] 
FROM [ItemMaster] 
WHERE [Item_Name] = @OldName

UPDATE [ItemMaster]
SET [Item_Name] = @ItemName,
	[SKU_Code] = @SKU,
	[Description]= @Desc
WHERE [Item_ID]= @ID
		
	
END
GO
