USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_UserMaster_GetUsers]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_UserMaster_GetUsers]

AS
BEGIN
DECLARE @RowCount int

SELECT @RowCount = COUNT([Username]) 
FROM [UserMaster]

SELECT [Username], @RowCount 'RowCount' 
FROM [UserMaster]

END
GO
