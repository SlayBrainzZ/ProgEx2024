USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_Get]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_AccountMaster_Get]

AS
BEGIN

SELECT [AccountID], [Name], [City], [EmailId], [Phone_Number], [Balance_Amount]
FROM [AccountMaster]
ORDER BY [AccountID] ASC 

END
GO
