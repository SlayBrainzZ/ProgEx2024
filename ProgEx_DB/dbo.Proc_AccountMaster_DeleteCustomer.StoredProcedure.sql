USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_DeleteCustomer]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_AccountMaster_DeleteCustomer] --'Nis'

(@CusName varchar(100) 
)

AS

BEGIN

IF EXISTS (SELECT NULL 
			FROM [AccountMaster] 
			WHERE [Name] = @CusName)
BEGIN
		
DELETE FROM [AccountMaster]
WHERE [Name] = @CusName

SELECT '1' 'output'
END

ELSE 

BEGIN
SELECT '0' 'output'

END
END
GO
