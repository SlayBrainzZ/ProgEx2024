USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_Insert]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_AccountMaster_Insert] 

(@Name varchar(100),
@AccType varchar(5),
@City varchar(200),
@BalanceAmt decimal(16,2)
)

AS

BEGIN

INSERT INTO AccountMaster ([Name], [Account_Type], [City], [Balance_Amount]) 
VALUES					   (@Name, @AccType, @City, @BalanceAmt)

SELECT SCOPE_IDENTITY() [AccountID]

END
GO
