USE [Prog_Ex]
GO
DROP PROCEDURE IF EXISTS [dbo].[Proc_AccountMaster_UpdateCusDetails]
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_UpdateCusDetails]   
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_AccountMaster_UpdateCusDetails]-- 'Aman', 'at1@gmail.com', '9191348', 'Noida'

(
@CusID int,
@CusName varchar(200),
@NewName varchar(200),
@Email varchar(100),
@Phone varchar(20),
@City varchar(100)
)
AS

BEGIN

UPDATE [AccountMaster]
SET [Name]= @NewName, [Email] = @Email, [Phone_Number] = @Phone, [City] = @City
WHERE [AccountID] = @CusID

END
GO
