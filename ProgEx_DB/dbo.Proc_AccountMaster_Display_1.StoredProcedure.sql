USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_Display_1]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- For Class DeleteFromDatabase (Eclipse)
CREATE PROC [dbo].[Proc_AccountMaster_Display_1]
AS

BEGIN 

SELECT [AccountID], [Name], [City], [Balance_Amount]
FROM [AccountMaster]

END

GO
