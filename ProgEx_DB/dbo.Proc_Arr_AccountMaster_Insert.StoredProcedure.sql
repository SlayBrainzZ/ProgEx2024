USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_Arr_AccountMaster_Insert]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[Proc_Arr_AccountMaster_Insert]
(@Name varchar(100),
@Account_Type varchar(5),
@Balance_Amount decimal(15,2),
@City varchar(100)
)

AS
BEGIN 

INSERT INTO [AccountMaster]([Name], [Account_Type], [Balance_Amount], [City])
VALUES					 (@Name, @Account_Type, @Balance_Amount, @City)	

END
GO
