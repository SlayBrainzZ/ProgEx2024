USE [Prog_Ex]
GO
/****** Object:  StoredProcedure [dbo].[Proc_CategoryMaster_Insert]    Script Date: 23/05/2024 02:00:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_CategoryMaster_Insert]

(@Ctg varchar(100))

AS
BEGIN

INSERT INTO [CategoryMaster] (CategoryName)
VALUES						 (@Ctg)


END
GO
