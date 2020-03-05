use DbInvestment;

--password = password
INSERT INTO [dbo].[Users] ([Username] ,[Password] ,[Enabled]) VALUES ('user','pwd',1);
INSERT INTO [dbo].[Users] ([Username] ,[Password] ,[Enabled]) VALUES ('admin','pwd',1);
INSERT INTO [dbo].[Role] ([Id],[Name]) VALUES (1,'USER');
INSERT INTO [dbo].[Role] ([Id],[Name]) VALUES (2,'ADMIN');
INSERT INTO [dbo].[UsersRole] ([IdUser],[IdRole]) VALUES (1, 1);
INSERT INTO [dbo].[UsersRole] ([IdUser],[IdRole]) VALUES (2, 2);

           





