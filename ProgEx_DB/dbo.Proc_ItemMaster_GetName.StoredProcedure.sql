USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_ItemMaster_GetName]    Script Date: 23/05/2024 02:00:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_ItemMaster_GetName]
AS

BEGIN
DECLARE @RowCount int

SELECT @RowCount = COUNT([Item_Name])
FROM [ItemMaster] 
WHERE [Item_ID] NOT IN (SELECT [Item_ID]
						FROM [TransactionMaster])

SELECT [Item_Name], @RowCount 'RowCount' 
FROM [ItemMaster] 
WHERE [Item_ID] NOT IN (SELECT [Item_ID] 
						FROM [TransactionMaster])
		
	
END
GO
