USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_GetCusNames]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_GetCusNames]

AS
BEGIN
DECLARE @RowCount int

SELECT @RowCount = COUNT([Name])
FROM [AccountMaster]

SELECT [Name], @RowCount 'RowCount' 
FROM [AccountMaster]

END
GO
