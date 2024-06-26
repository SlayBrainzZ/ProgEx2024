USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_ItemMaster_ItemReport]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_ItemMaster_ItemReport] --'Tableware'

(@Ctg varchar(100)
)

AS
BEGIN

DECLARE @CtgID int

SELECT  @CtgID = [CategoryID] 
FROM [CategoryMaster] 
WHERE [CategoryName] = @Ctg


SELECT im.[Item_Name], im.[Description], im.[SKU_Code] 
FROM [ItemMaster] im 
JOIN [CategoryMaster] cm
ON im.[CategoryID] = cm.[CategoryID]
WHERE im.[CategoryID] = @CtgID

END
GO
