USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_ItemMaster_Insert]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_ItemMaster_Insert] -- 'Dinner Set of 3', 'APP-123', 'Porcelain', 'Tableware'

(@Item varchar(100),
@SKU varchar(100),
@Description varchar(200),
@CatName varchar(100)
)
AS

BEGIN
DECLARE @CatID int

IF NOT EXISTS(SELECT NULL 
				FROM [ItemMaster] 
				WHERE [SKU_Code] = @SKU)
	
	BEGIN
		SELECT  @CatID= [CategoryID] 
		FROM [CategoryMaster] 
		WHERE [CategoryName] = @CatName

		INSERT INTO [ItemMaster]([Item_Name], [SKU_Code], [Description], [CategoryID]) 
						VALUES (@Item, @SKU, @Description, @CatID)
		SELECT '1' 'out'
	END
ELSE 
	BEGIN
		SELECT '0' 'out'
	END

END
GO
