USE [Prog_Ex]
GO
DROP PROCEDURE IF EXISTS [dbo].[Proc_Display_AccountMaster]
--Object:  StoredProcedure [dbo].[Proc_Display_AccountMaster]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_Display_AccountMaster]

AS

BEGIN

SELECT [AccountID], [Account_Type], [Name], [City], [Balance_Amount] 
FROM [AccountMaster]


END
GO
