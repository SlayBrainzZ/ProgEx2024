USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_Arr_SalesMaster_Insert]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE Proc [dbo].[Proc_Arr_SalesMaster_Insert]
(
@TranDate datetime,
@Amt decimal(16,2)
)
AS

BEGIN

INSERT INTO [SalesMaster]([TransactionDate], [Amount])
VALUES					(@TranDate, @Amt)

END

GO
