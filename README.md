# springbootdemo 
基于spring boot+spring mvc+mybatis+vue开发设计的前后端分离的电商购物场景的demo网站

***

该DEMO的搭建过程与涉及的知识点，可以参见我的这篇博文：[JAVA WEB快速入门之从编写一个基于SpringBoot+Mybatis快速创建的REST API项目了解SpringBoot、SpringMVC REST API、Mybatis等相关知识](https://www.cnblogs.com/zuowj/p/10335080.html)**

如下是示例项目中所涉及的到表SQL创建脚本（SQL SERVER）：

```mssql

CREATE TABLE [dbo].[TA_TestShopUser](
	[userId] [varchar](20) NOT NULL,
	[passwordHash] [nvarchar](100) NOT NULL,
	[nickName] [nvarchar](10) NOT NULL,
	[depositAmount] [decimal](18, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO



CREATE TABLE [dbo].[TA_TestGoods](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](50) NULL,
	[picture] [nchar](100) NULL,
	[price] [decimal](18, 2) NOT NULL,
	[introduction] [nvarchar](max) NULL,
	[categoryId] [int] NOT NULL,
	[lastEditBy] [nvarchar](20) NULL,
	[lastEditTime] [datetime] NULL,
 CONSTRAINT [PK__TA_TestG__3213E83F8CD13B00] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

ALTER TABLE [dbo].[TA_TestGoods] ADD  CONSTRAINT [DF__TA_TestGo__lastE__4A79EC46]  DEFAULT (getdate()) FOR [lastEditTime]
GO



CREATE TABLE [dbo].[TA_TestGoodsCategory](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[categoryName] [nvarchar](20) NOT NULL,
	[goodsCount] [int] NULL,
	[serialNo] [int] NOT NULL,
	[lastEditBy] [nvarchar](20) NULL,
	[lastEditTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[TA_TestGoodsCategory] ADD  DEFAULT ((1)) FOR [serialNo]
GO

ALTER TABLE [dbo].[TA_TestGoodsCategory] ADD  DEFAULT (getdate()) FOR [lastEditTime]
GO



CREATE TABLE [dbo].[TA_TestShoppingCart](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[shopper] [nvarchar](20) NOT NULL,
	[goodsId] [int] NOT NULL,
	[qty] [int] NOT NULL,
	[addedTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[TA_TestShoppingCart] ADD  DEFAULT ((1)) FOR [qty]
GO

ALTER TABLE [dbo].[TA_TestShoppingCart] ADD  DEFAULT (getdate()) FOR [addedTime]
GO



CREATE TABLE [dbo].[TA_TestShoppingOrder](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[shopper] [nvarchar](20) NOT NULL,
	[totalQty] [int] NOT NULL,
	[totalPrice] [decimal](18, 2) NOT NULL,
	[isCompleted] [bit] NOT NULL,
	[createBy] [nvarchar](20) NULL,
	[createTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[TA_TestShoppingOrder] ADD  DEFAULT ((0)) FOR [isCompleted]
GO

ALTER TABLE [dbo].[TA_TestShoppingOrder] ADD  DEFAULT (getdate()) FOR [createTime]
GO



CREATE TABLE [dbo].[TA_TestShoppingOrderDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[shoppingOrderId] [int] NOT NULL,
	[goodsId] [int] NOT NULL,
	[qty] [int] NOT NULL,
	[totalPrice] [decimal](18, 2) NOT NULL,
	[createBy] [nvarchar](20) NULL,
	[createTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[TA_TestShoppingOrderDetail] ADD  DEFAULT ((1)) FOR [qty]
GO

ALTER TABLE [dbo].[TA_TestShoppingOrderDetail] ADD  DEFAULT (getdate()) FOR [createTime]
GO


```

