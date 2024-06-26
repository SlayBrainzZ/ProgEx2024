USE [Prog_Ex]
GO
DROP PROCEDURE IF EXISTS [dbo].[Proc_AccountMaster_Display]
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_Display]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_AccountMaster_Display]

(@AccMasTypeID int)

AS
BEGIN


SELECT amt.[Description] , am.[AccountID], am.[Name], am.[Address1], am.[Balance_Amount] 
FROM [AccountMaster] am
JOIN [AccountMasterTypeID] amt
ON am.Account_Type = amt.AccMasTypeID
WHERE amt.[AccMasTypeID] = @AccMasTypeID

END
GO
