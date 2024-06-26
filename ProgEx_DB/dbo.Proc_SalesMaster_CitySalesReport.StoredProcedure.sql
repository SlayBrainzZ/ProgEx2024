USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_CitySalesReport]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_SalesMaster_CitySalesReport]

AS
BEGIN
DECLARE @RowCount int

SELECT @RowCount = COUNT(DISTINCT [City])  
FROM [AccountMaster]

SELECT DISTINCT [City], @RowCount 'RowCount' 
FROM [AccountMaster]

END
GO
