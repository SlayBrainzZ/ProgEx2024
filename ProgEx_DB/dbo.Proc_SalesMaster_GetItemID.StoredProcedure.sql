USE [Prog_Ex]
GO
/****** Object:  StoredProcedure [dbo].[Proc_SalesMaster_GetItemID]    Script Date: 23/05/2024 02:00:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_GetItemID]

(@Item varchar(100))

AS

BEGIN

SELECT [Item_ID]
FROM [ItemMaster] 
WHERE [Item_Name] = @Item

END
GO
