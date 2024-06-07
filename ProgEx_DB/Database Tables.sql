USE [Prog_Ex]

SET ANSI_NULLS OFF

SET QUOTED_IDENTIFIER ON

--Create Tabels:
Print('CREATE TABELS')

--Object:  Table [dbo].[AccountMaster]
PRINT('Create [AccountMaster]')
Drop Table if Exists [dbo].[AccountMaster];
CREATE TABLE [dbo].[AccountMaster](
	[Name] nvarchar(50) NOT NULL,
	[AccountID] int IDENTITY(1,1) NOT NULL,
	[Phone_Number] nvarchar(20) NOT NULL,
	[Email_ID] nvarchar(20) NOT NULL,
	[Balance_Amount] decimal(10, 2) NOT NULL,
	[Address1] nvarchar(MAX) NOT NULL,
	[Address2] nvarchar(MAX) NOT NULL,
	[Address3] nvarchar(MAX) NOT NULL,
	[City] nvarchar (300) NOT NULL,
	[Account_Type] nvarchar (50) NOT NULL
 CONSTRAINT [PK_AccountMaster_AccID] PRIMARY KEY CLUSTERED

(
	[AccountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) 


--Object:  Table [dbo].[Admin]
PRINT('Create [Admin]')
DROP TABLE IF EXISTS [dbo].[Admin];
CREATE TABLE [dbo].[Admin](
	[username] nvarchar(100) NOT NULL,
	[password] nvarchar(100) NOT NULL,
	[UserID] int IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_Username] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) 

PRINT('Create [CateryMaster]')
DROP TABLE IF EXISTS [dbo].[CateryMaster];
CREATE TABLE [dbo].[CateryMaster](
	[CateryID] int IDENTITY(1,1) NOT NULL,
	[CateryName] nvarchar(50) NOT NULL,
 CONSTRAINT [PK_CateryMaster_CtgID] PRIMARY KEY CLUSTERED 
(
	[CateryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) 

--Object:  Table [dbo].[Customer_Info]  
PRINT('Create [Customer_Info]')
DROP TABLE IF EXISTS [dbo].[Customer_Info]
CREATE TABLE [dbo].[Customer_Info](
	[Name] nvarchar (100) NOT NULL,
	[Mobile] int  NOT NULL,
	[CustomerID] int NOT NULL,
 CONSTRAINT [PK_CustomerID] PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) 

--Object:  Table [dbo].[ExceptionMaster]
PRINT('Create [ExceptionMaster]')
DROP TABLE IF EXISTS [dbo].[ExceptionMaster];
CREATE TABLE [dbo].[ExceptionMaster](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Exception_Details] nvarchar(50) NOT NULL,
	[Exception_Date] date NOT NULL,
	[FormName] nvarchar(200) NOT NULL,
	[MethodName] nvarchar(200) NOT NULL,
 CONSTRAINT [PK_ExceptionMaster_ExceptionID] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) 

--Object:  Table [dbo].[ItemMaster]
PRINT('Create [ItemMaster]')
DROP TABLE IF EXISTS [dbo].[ItemMaster]
CREATE TABLE [dbo].[ItemMaster](
	[Item_ID] [int] IDENTITY(1,1) NOT NULL,
	[Item_Name] nvarchar(500) NOT NULL,
	[Description] nvarchar(1000) NOT NULL,
	[CateryID] [int] NOT NULL,
	[SKU_Code] nvarchar(100) NOT NULL,
 CONSTRAINT [PK_ItemMaster_ItemID] PRIMARY KEY CLUSTERED 
(
	[Item_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) 
ALTER TABLE [dbo].[ItemMaster]  WITH CHECK ADD  CONSTRAINT [FK_ItemMaster_CtgID] FOREIGN KEY([CateryID])
REFERENCES [dbo].[CateryMaster] ([CateryID])

ALTER TABLE [dbo].[ItemMaster] CHECK CONSTRAINT [FK_ItemMaster_CtgID]

--Object:  Table [dbo].[Manage_Stock]
--PRINT('Create [Manage_Stock]')
--DROP TABLE IF EXISTS [dbo].[Manage_Stock];
--CREATE TABLE [dbo].[Manage_Stock](
--	[SupplierName] nvarchar(100) NOT NULL,
--	[ProductID] int NOT NULL,
--	[ProductName] nvarchar(100) NOT NULL,
--	[TruckNumber] nvarchar(100) NOT NULL,
--	[CurrentQtty] int NOT NULL
--) ON [PRIMARY]

-- Wieso haben wir die Tabelle 2. mal? Gibt es dafür einen Grund?
--Object:  Table [dbo].[ManageStock]
PRINT('Create [ManageStock]')
DROP TABLE IF EXISTS [dbo].[ManageStock]
CREATE TABLE [dbo].[ManageStock](
	[ProductID] [int] NOT NULL,
	[ProductName] nvarchar (100) NOT NULL,
	[SupplierName] nvarchar (100) NOT NULL,
	[TruckNumber] nvarchar (100) NOT NULL,
	[CurrentQtty] int NOT NULL,
 CONSTRAINT [PK_ProductID] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
)

--Object:  Table [dbo].[MonthMaster]
PRINT('Create [MonthMaster]')
DROP TABLE IF EXISTS [dbo].[MonthMaster];
CREATE TABLE [dbo].[MonthMaster](
	[MonthName] nvarchar(50) NOT NULL,
	[Month_ID] int identity(1,1) NOT NULL
 CONSTRAINT [PK_MonthMaster_MonthID] PRIMARY KEY CLUSTERED
(
	[Month_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) 


--Object:  Table [dbo].[Sales_Info]
PRINT('Create [Sales_Info]')
DROP TABLE IF EXISTS [dbo].[Sales_Info];
CREATE TABLE [dbo].[Sales_Info](
	[Price] decimal(10, 2) NOT NULL,
	[Quantity] int NOT NULL,
	[PurchaseDate] datetime NOT NULL,
	[Purchasetime] time(7) NOT NULL,
	[DueDate] datetime NOT NULL,
	[CustomerID] int NOT NULL,
	[InvoiceID] int NOT NULL,
 CONSTRAINT [PK_InvoiceID] PRIMARY KEY CLUSTERED 
(
	[InvoiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
)

ALTER TABLE [dbo].[Sales_Info]  WITH CHECK ADD  CONSTRAINT [FK_CustomerID] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customer_Info] ([CustomerID])

ALTER TABLE [dbo].[Sales_Info] CHECK CONSTRAINT [FK_CustomerID]


--Object:  Table [dbo].[SalesMaster]
PRINT('Create [SalesMaster]')
DROP TABLE IF EXISTS [dbo].[SalesMaster];
CREATE TABLE [dbo].[SalesMaster](
	[TransactionDate] date NOT NULL,
	[Amount] decimal(16, 2) NOT NULL,
	[AccountID] int NOT NULL,
	[SalesID] int IDENTITY(1,1) NOT NULL,
 CONSTRAINT PK_SalesMaster_SalesID PRIMARY KEY CLUSTERED 
(
	[SalesID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) 
ALTER TABLE [dbo].[SalesMaster]  WITH CHECK ADD  CONSTRAINT [FK_SalesMaster_AccID] FOREIGN KEY([AccountID])
REFERENCES [dbo].[AccountMaster] ([AccountID])

ALTER TABLE [dbo].[SalesMaster] CHECK CONSTRAINT [FK_SalesMaster_AccID]

--Object:  Table [dbo].[TransactionMaster]
PRINT('Create [TransactionMaster]')
DROP TABLE IF EXISTS [dbo].[TransactionMaster]
CREATE TABLE [dbo].[TransactionMaster](
	[Item_ID] int NOT NULL,
	[Quantity] int NOT NULL,
	[Rate] decimal(10, 2) NOT NULL,
	[Date] date NOT NULL,
	[TransactionID] int IDENTITY(1,1) NOT NULL,
	[SalesID] int NOT NULL,
	[TransactionType] nvarchar (50) NOT NULL
 CONSTRAINT [PK_TransactionMaster_TranID] PRIMARY KEY CLUSTERED 
(
	[TransactionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) 
ALTER TABLE [dbo].[TransactionMaster]  WITH CHECK ADD  CONSTRAINT [FK_TransactionMaster_ItemID] FOREIGN KEY([Item_ID])
REFERENCES [dbo].[ItemMaster] ([Item_ID])

ALTER TABLE [dbo].[TransactionMaster] CHECK CONSTRAINT [FK_TransactionMaster_ItemID]

ALTER TABLE [dbo].[TransactionMaster]  WITH CHECK ADD  CONSTRAINT [FK_TransactionMaster_SalesID] FOREIGN KEY([SalesID])
REFERENCES [dbo].[SalesMaster] ([SalesID])

ALTER TABLE [dbo].[TransactionMaster] CHECK CONSTRAINT [FK_TransactionMaster_SalesID]

--Object:  Table [dbo].[UserMaster]
PRINT('Create [UserMaster]')
DROP TABLE IF EXISTS [dbo].[UserMaster]
CREATE TABLE [dbo].[UserMaster](
	[Name] nvarchar(50) NOT NULL,
	[Phone_Number] nvarchar(50) NOT NULL,
	[Address] nvarchar(50) NOT NULL,
	[Email_ID] nvarchar(50) NOT NULL,
	[Password] nvarchar(100) NOT NULL,
	[Username] nvarchar(100) NOT NULL,
	[IsActive] [int] NOT NULL,
	[UserID] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_UserMaster_UserID] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
)

--Object:  Table [dbo].[DayMaster]
PRINT('Create [DayMaster]')
Drop Table if Exists [dbo].[DayMaster];
CREATE TABLE [dbo].[DayMaster](
	[Day] nvarchar(30) NOT NULL,
	[Day_ID] int identity(1,1) NOT NULL
 CONSTRAINT [PK_DayMaster_DayID] PRIMARY KEY CLUSTERED

(
	[DAY_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) 

--Object:  Table [dbo].[AccountMasterTypeID]
PRINT('Create [AccountMasterTypeID]')
Drop Table if Exists [dbo].[AccountMasterTypeID];
CREATE TABLE [dbo].[AccountMasterTypeID](
	[AccMasTypeID] int identity(1,1) NOT NULL,
	[Description] nvarchar(50) NOT NULL
 CONSTRAINT [PK_AccMasType_AccMasTypeID] PRIMARY KEY CLUSTERED
(
	[AccMasTypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) 

--Object:  Table [dbo].[TransactionMasterTypeID]
PRINT('Create [TransactionMasterTypeID]')
Drop Table if Exists [dbo].[TransactionMasterTypeID];
CREATE TABLE [dbo].[TransactionMasterTypeID](
	[TransMasTypeID] int identity(1,1) NOT NULL,
	[Description] nvarchar(50) NOT NULL
 CONSTRAINT [PK_TransMasType_AccMasTypeID] PRIMARY KEY CLUSTERED
(
	[TransMasTypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) 

