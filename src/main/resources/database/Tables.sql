create database Investment;

use Investment;

-- tables used to save user information that will access the services
create table dbo.Users (
	Id int not null IDENTITY(1,1) primary key,
	Username nvarchar(100) not null, 
	Password nvarchar(500) not null,
	Enabled bit not null
);
alter table Users add constraint UQ_Users unique (Username);

create table dbo.Role(
	Id int not null primary key,
	Name nvarchar(50) not null
);
alter table Role add constraint UQ_Role unique (Name); 

create table dbo.UsersRole (
	IdUser int not null,
	IdRole int not null
);
alter table UsersRole add constraint PK_UsersRole primary key (IdUser,IdRole);
alter table UsersRole add constraint FK_UsersRole_User foreign key (IdUser) references Users(Id);
alter table UsersRole add constraint FK_UsersRole_Role foreign key (IdRole) references Role(Id);


-- table to save access access token information
create table oauth_access_token (
  token_id VARCHAR(255),
  token varbinary(max),
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication varbinary(max),
  refresh_token VARCHAR(255)
);


create table oauth_refresh_token (
  token_id VARCHAR(255),
  token varbinary(max),
  authentication varbinary(max)
);