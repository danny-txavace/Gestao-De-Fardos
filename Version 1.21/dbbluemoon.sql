create database dbbluemoon;
use dbbluemoon;

create table tbusuario (
ID int auto_increment not null,
NOME varchar(100) unique not null,
PHONE_1 varchar(50) null,
PHONE_2 varchar(50) null,
USUÁRIO varchar(50) unique not null,
SENHA varchar(50) not null,
PERFIL varchar(30) not null,
LOJA varchar(50) not null,
DATA_HORA datetime default current_timestamp,
primary key(ID)
);

DELIMITER //
create procedure Proced_Organizartbusuario()
begin
	alter table tbusuario drop ID;
    alter table tbusuario auto_increment = 1;
    alter table tbusuario order by NOME ASC;
    alter table tbusuario add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

insert into tbusuario(NOME, PHONE_1, PHONE_2, USUÁRIO, SENHA, PERFIL, LOJA) values("Ramadan Ibraimo Ismael", "+258 84 962 6719", "+258 87 171 7834", "ramadan", "5126", "admin", "Loja 1");

create table tbcliente (
ID int auto_increment not null,
NOME varchar(50) not null,
PHONE_1 varchar(50) null,
PHONE_2 varchar(50) null,
EMAIL varchar(50) null,
LOJA varchar(50) not null,
DESCRIÇÃO varchar(500) null,
DATA_HORA datetime default current_timestamp,
primary key(ID)
);

DELIMITER //
create procedure Proced_Organizartbcliente()
begin
	alter table tbcliente drop ID;
    alter table tbcliente auto_increment = 1;
    alter table tbcliente order by NOME ASC;
    alter table tbcliente add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

create table tbcategoria (
ID int auto_increment not null,
CATEGORIA varchar(100) unique not null,
DATA_HORA datetime default current_timestamp,
primary key(ID)
);

DELIMITER //
create procedure Proced_Organizartbcategoria()
begin
	alter table tbcategoria drop ID;
    alter table tbcategoria auto_increment = 1;
    alter table tbcategoria order by DATA_HORA DESC;
    alter table tbcategoria add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

create table tbmarca (
ID int auto_increment not null,
MARCA varchar(100) unique not null,
DATA_HORA datetime default current_timestamp,
primary key(ID)
);

DELIMITER //
create procedure Proced_Organizartbmarca()
begin
	alter table tbmarca drop ID;
    alter table tbmarca auto_increment = 1;
    alter table tbmarca order by DATA_HORA DESC;
    alter table tbmarca add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

create table tbloja_1 (
ID int auto_increment not null,
DESIGNAÇÃO varchar(200) unique not null,
MARCA varchar(100) null,
CATEGORIA varchar(100) not null,
UNIDADE varchar(70) null,
QUANTIDADE int not null,
PRECO_ARTIGO_MZN decimal(10,2) not null,
PRECO_TOTAL_MZN decimal(10,2) generated always as(QUANTIDADE * PRECO_ARTIGO_MZN) virtual not null,
PRECO_ARTIGO_USD decimal(10,2) not null,
PRECO_TOTAL_USD decimal(10,2) generated always as(QUANTIDADE * PRECO_ARTIGO_USD) virtual not null,
DATA_HORA datetime default current_timestamp,
primary key(ID)
);

DELIMITER //
create procedure Proced_Organizartbloja_1()
begin
	alter table tbloja_1 drop ID;
    alter table tbloja_1 auto_increment = 1;
    alter table tbloja_1 order by DESIGNAÇÃO ASC;
    alter table tbloja_1 add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

create table tbloja_2 like tbloja_1;

DELIMITER //
create procedure Proced_Organizartbloja_2()
begin
	alter table tbloja_2 drop ID;
    alter table tbloja_2 auto_increment = 1;
    alter table tbloja_2 order by DESIGNAÇÃO ASC;
    alter table tbloja_2 add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

create table tbloja_3 like tbloja_2;

DELIMITER //
create procedure Proced_Organizartbloja_3()
begin
	alter table tbloja_3 drop ID;
    alter table tbloja_3 auto_increment = 1;
    alter table tbloja_3 order by DESIGNAÇÃO ASC;
    alter table tbloja_3 add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

create table tbloja_jardim like tbloja_3;

DELIMITER //
create procedure Proced_Organizartbloja_jardim()
begin
	alter table tbloja_jardim drop ID;
    alter table tbloja_jardim auto_increment = 1;
    alter table tbloja_jardim order by DESIGNAÇÃO ASC;
    alter table tbloja_jardim add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

create table tbarmazem_beira like tbloja_1;

DELIMITER //
create procedure Proced_Organizartbarmazem_beira()
begin
	alter table tbarmazem_beira drop ID;
    alter table tbarmazem_beira auto_increment = 1;
    alter table tbarmazem_beira order by DESIGNAÇÃO ASC;
    alter table tbarmazem_beira add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

create table tbarmazem_maputo like tbarmazem_beira;

DELIMITER //
create procedure Proced_Organizartbarmazem_maputo()
begin
	alter table tbarmazem_maputo drop ID;
    alter table tbarmazem_maputo auto_increment = 1;
    alter table tbarmazem_maputo order by DESIGNAÇÃO ASC;
    alter table tbarmazem_maputo add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

create table tbvenda_1 (
ID int auto_increment not null,
DESIGNAÇÃO varchar(100) not null,
MARCA varchar(100) null,
CATEGORIA varchar(100) not null,
UNIDADE varchar(30) null,
QUANTIDADE int not null,
PRECO_ARTIGO_MZN decimal(10,2) not null,
PRECO_TOTAL_MZN decimal(10,2) generated always as (QUANTIDADE * PRECO_ARTIGO_MZN) virtual not null,
PRECO_ARTIGO_USD decimal(10,2) not null,
PRECO_TOTAL_USD decimal(10,2) generated always as (QUANTIDADE * PRECO_ARTIGO_USD) virtual not null,
PRECO_VENDA decimal(10,2) not null,
LUCRO decimal(10,2) generated always as (PRECO_VENDA - PRECO_TOTAL_MZN) virtual not null,
VENDIDO_POR varchar(100) not null,
DATA_HORA datetime default current_timestamp,
primary key(ID)
);

DELIMITER //
create procedure Proced_Organizartbvenda_1()
begin
	alter table tbvenda_1 drop ID;
    alter table tbvenda_1 auto_increment = 1;
    alter table tbvenda_1 order by DATA_HORA DESC;
    alter table tbvenda_1 add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

-- TBL  V E N D A _ 2
create table tbvenda_2 like tbvenda_1;

DELIMITER //
create procedure Proced_Organizartbvenda_2()
begin
	alter table tbvenda_2 drop ID;
    alter table tbvenda_2 auto_increment = 1;
    alter table tbvenda_2 order by DATA_HORA DESC;
    alter table tbvenda_2 add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

create table tbvenda_3 like tbvenda_2;

DELIMITER //
create procedure Proced_Organizartbvenda_3()
begin
	alter table tbvenda_3 drop ID;
    alter table tbvenda_3 auto_increment = 1;
    alter table tbvenda_3 order by DATA_HORA DESC;
    alter table tbvenda_3 add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

create table tbvenda_jardim like tbvenda_3;

DELIMITER //
create procedure Proced_Organizartbvenda_jardim()
begin
	alter table tbvenda_jardim drop ID;
    alter table tbvenda_jardim auto_increment = 1;
    alter table tbvenda_jardim order by DATA_HORA DESC;
    alter table tbvenda_jardim add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

create table tbidioma (
ID int auto_increment not null,
IDIOMA varchar(20) unique not null,
ESTADO boolean not null,
primary key(ID)
);

DELIMITER //
create procedure Proced_Organizartbidioma()
begin
	alter table tbidioma drop ID;
    alter table tbidioma auto_increment = 1;
    alter table tbidioma order by IDIOMA ASC;
    alter table tbidioma add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

create table tblogin_attempts (
ID int auto_increment not null,
USUÁRIO varchar(50) not null,
DATA_HORA timestamp default current_timestamp,
primary key(ID)
);

DELIMITER //
create procedure Proced_Organizartblogin_attempts()
begin
	alter table tblogin_attempts drop ID;
    alter table tblogin_attempts auto_increment = 1;
    alter table tblogin_attempts order by DATA_HORA DESC;
    alter table tblogin_attempts add ID int unsigned not null auto_increment primary key first;
end;
//
DELIMITER ;

create table tbcontrolleruser (
id int auto_increment not null,
cUser varchar(80) not null,
cPerfil varchar(20) not null,
primary key(id)
);

insert into tbidioma(IDIOMA, ESTADO) values("English", false), ("Português", true);