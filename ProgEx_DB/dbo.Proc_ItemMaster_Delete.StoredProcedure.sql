USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_ItemMaster_Delete]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_ItemMaster_Delete]

(@ItemName varchar(100))
AS

BEGIN
IF EXISTS(SELECT NULL 
			FROM [ItemMaster] 
			WHERE [Item_Name] = @ItemName)
	BEGIN
		DELETE FROM [ItemMaster]
		WHERE [Item_Name] = @ItemName
		SELECT '1' 'out'
	END
ELSE
	BEGIN
		SELECT '0' 'out'
	END
END
GO
