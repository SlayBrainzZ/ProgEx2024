USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_Dsiplay_AccountMaster]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_Dsiplay_AccountMaster]

AS

BEGIN

SELECT [AccountID], [Account_Type], [Name], [City], [Balance_Amount] 
FROM [AccountMaster]


END
GO
