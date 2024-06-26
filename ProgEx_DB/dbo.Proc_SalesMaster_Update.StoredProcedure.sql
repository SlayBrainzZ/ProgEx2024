USE [Prog_Ex]
GO
--Object:  StoredProcedure [dbo].[Proc_SalesMaster_Update]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Proc_SalesMaster_Update] 

(@SalesID int,
@ItemID int,
@Date date,
@Qtty int,
@Rate decimal(10,2)
)

AS

BEGIN

IF EXISTS(SELECT NULL 
			FROM [SalesMaster]
			WHERE [SalesID] = @SalesID)
	BEGIN
		UPDATE [SalesMaster]
		SET [Amount] = @Qtty*@Rate,
			[TransactionDate] = @Date
		WHERE [SalesID] = @SalesID

		UPDATE [TransactionMaster]
		SET [Quantity] = @Qtty,
			[Rate] = @Rate,
			[Date] = @Date,
			[Item_ID] = @ItemID
		WHERE [SalesID] = @SalesID

		SELECT '1' 'out' 
		FROM [AccountMaster] 
	END

ELSE
	BEGIN
		SELECT '0' 'out'
	END


END
GO
