create database if not exists MyApp;

use MyApp;

drop table if exists MyTable;
create table if not exists MyTable (
  pkId int primary key,
  uName varchar(20),
  uPass varchar(20)
);

use MyApp;

insert into MyTable values
 (1,'zhangsan','111111'),
 (2,'lisi','222222')
;

select * from MyTable;
