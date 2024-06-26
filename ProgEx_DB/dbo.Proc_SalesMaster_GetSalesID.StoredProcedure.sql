USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_GetSalesID]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_GetSalesID] --'AT'

(@CusName varchar(100))

AS
BEGIN

DECLARE @CusID int 
DECLARE @RowCount int

SELECT @CusID = [AccountID] 
FROM [AccountMaster] 
WHERE [Name] = @CusName

SELECT @RowCount = COUNT([SalesID]) 
FROM [SalesMaster] 
WHERE [AccountID] = @CusID

SELECT [SalesID], @RowCount 'RowCount' 
FROM [SalesMaster]
WHERE [AccountID] = @CusID

END
GO
