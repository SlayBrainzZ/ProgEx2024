USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_Display]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_AccountMaster_Display]
AS
BEGIN

SELECT [AccountID], [Name], [Address1], [Balance_Amount] 
FROM [AccountMaster]
WHERE [Account_Type] = 'C' 

END
GO
