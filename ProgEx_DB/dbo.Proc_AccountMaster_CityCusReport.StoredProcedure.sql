USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_CityCusReport]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_AccountMaster_CityCusReport] --'Gurgaon'
(
@City varchar(100)
)

AS
BEGIN

SELECT COUNT([AccountID]) 'TotalCus' 
FROM [AccountMaster] 
WHERE [City] = @City

END
GO
