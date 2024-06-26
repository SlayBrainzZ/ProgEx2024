USE [Prog_Ex]
GO
DROP PROCEDURE IF EXISTS [dbo].[Proc_AccountMaster_GetCusDetails]
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_GetCusDetails]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_AccountMaster_GetCusDetails] -- 1002

(@CusID int)
AS

BEGIN

SELECT [Email], [Phone_Number], [City]
FROM [AccountMaster]
WHERE [AccountID] = @CusID

END
GO
