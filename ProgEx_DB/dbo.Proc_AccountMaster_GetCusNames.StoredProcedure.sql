USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_GetCusNames]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_AccountMaster_GetCusNames]

AS

BEGIN
DECLARE @RowCount int

SELECT @RowCount =  COUNT([Name]) 
FROM [AccountMaster]

SELECT [Name], [AccountID], @RowCount 'RowCount' 
FROM [AccountMaster] 

END
GO
