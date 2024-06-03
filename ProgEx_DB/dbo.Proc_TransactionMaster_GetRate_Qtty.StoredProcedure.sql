USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_TransactionMaster_GetRate_Qtty]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_TransactionMaster_GetRate_Qtty]

(@SalesID int)
AS
BEGIN

SELECT [Rate],[Quantity] 
FROM [TransactionMaster] 
WHERE [SalesID] = @SalesID

END
GO
