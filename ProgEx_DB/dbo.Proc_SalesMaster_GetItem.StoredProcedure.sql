USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_GetItem]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_GetItem]
-- Get item name when inserting sales details

AS
BEGIN

DECLARE @RowCount int
SELECT @RowCount = COUNT([Item_Name]) 
FROM [ItemMaster]

SELECT @RowCount 'RowCount', [Item_Name] 
FROM [ItemMaster]

END
GO
