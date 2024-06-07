USE Prog_Ex
GO
DROP PROCEDURE IF EXISTS [dbo].[Proc_Display_TransactionMaster]
--Object:  StoredProcedure [dbo].[Proc_Display_TransactionMaster]   
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_Display_TransactionMaster]

AS

BEGIN

SELECT [TransactionID], [SalesID], [TransactionType], [Item_ID], [Quantity], [Rate], [Date]  
FROM [TransactionMaster]


END
GO
