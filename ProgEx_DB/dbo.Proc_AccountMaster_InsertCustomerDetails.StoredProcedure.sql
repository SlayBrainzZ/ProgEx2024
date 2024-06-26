USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_AccountMaster_InsertCustomerDetails]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Proc_AccountMaster_InsertCustomerDetails]
(@Name varchar(100),
@City varchar(100),
@AccType varchar(5)
)

AS

BEGIN

DECLARE 
@AccountID int

INSERT INTO [AccountMaster]([Name], [City], [Account_Type])
VALUES					  (@Name , @City, @AccType)

SELECT @AccountID = SCOPE_IDENTITY()
INSERT INTO [SalesMaster]([AccountID], [TransactionDate])
VALUES					(@AccountID, GETDATE() )


END
GO
