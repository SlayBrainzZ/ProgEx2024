USE Prog_Ex
GO
--Object:  StoredProcedure [dbo].[Proc_Dsiplay_TransactionMaster]   
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_Dsiplay_TransactionMaster]

AS

BEGIN

SELECT [TransactionID], [SalesID], [TransactionType], [Item_ID], [Quantity], [Rate], [Date]  
FROM [TransactionMaster]


END
GO
