USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_CategoryMaster_Update]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_CategoryMaster_Update] 

(@CtgID int,
@NewCtgName varchar(100)
)

AS

BEGIN

		UPDATE [CategoryMaster]
		SET [CategoryName] = @NewCtgName
		WHERE [CategoryID] = @CtgID

		
END
GO
