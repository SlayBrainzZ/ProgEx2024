USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_GetCusName]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_SalesMaster_GetCusName]

(@SalesID int)
AS
BEGIN
DECLARE @AccID int
DECLARE @CusName varchar(100)

IF EXISTS(SELECT NULL 
			FROM [SalesMaster] 
			WHERE [SalesID] = @SalesID)
	BEGIN
		SELECT sm.[AccountID], [Name], '1' 'out' 
		FROM [SalesMaster] sm 
		JOIN [AccountMaster] am
		ON sm.[AccountID] = am.[AccountID]
		WHERE [SalesID] = @SalesID

	END
ELSE
	BEGIN
		SELECT '0' 'out'
	END
END
GO
