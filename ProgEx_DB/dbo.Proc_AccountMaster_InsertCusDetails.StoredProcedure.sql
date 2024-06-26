USE [Prog_Ex]
GO
DROP PROCEDURE IF EXISTS [dbo].[Proc_AccountMaster_InsertCusDetails] ;
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_InsertCusDetails]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_AccountMaster_InsertCusDetails] 

( @Name varchar(50),
@Email varchar(100),
@Phone varchar(20),
@Add1 varchar(1500),
@Add2 varchar(1500),
@Add3 varchar(1500),
@City varchar(300)
)

AS
BEGIN

IF EXISTS(SELECT NULL 
			FROM [AccountMaster] 
			WHERE [Email] = @Email OR [Phone_Number] = @Phone)
	BEGIN
		IF EXISTS (SELECT NULL 
					FROM [AccountMaster] 
					WHERE [Phone_Number] = @Phone 
					AND [Email] = @Email)
			BEGIN
				SELECT '-2' 'output'
			END

		ELSE 
			BEGIN
				IF EXISTS (SELECT NULL 
				FROM [AccountMaster] 
				WHERE [Email] = @Email)
					BEGIN
						SELECT '-3' 'output'
					END
				ELSE 
					BEGIN
						SELECT '-4' 'output'
					END
			END
			
	END

ELSE 
	BEGIN
		INSERT INTO [AccountMaster]([Name], [Email], [Phone_Number], [Address1], [Address2], [Address3], [City])
							VALUES(@Name, @Email,@Phone, @Add1, @Add2, @Add3, @City)

		SELECT '1' 'output'
	END

END
GO
