
create table tipo_ativo(
	id_tipo_ativo int not null,
	desc_tipo_ativo varchar(30) not null,
	constraint PK_TIPO_ATIVO primary key (id_tipo_ativo)
);

create table ativo(
	id_ativo int auto_increment not null,
	cod_ativo varchar(10) not null unique,
	desc_ativo varchar(100) null,
 	id_tipo_ativo int not null,
	constraint PK_ATIVO primary key (id_ativo),
	constraint FK_ATIVO_TIPO_ATIVO foreign key (id_tipo_ativo) references tipo_ativo(id_tipo_ativo)
);

create table ordem(
	id_ordem int auto_increment not null,
	data_ordem date not null,
	preco float not null,
	quantidade int not null,
	total float not null,
	id_ativo int not null,
	constraint PK_ORDEM primary key(id_ordem),
	constraint FK_ORDEM_ATIVO foreign key (id_ativo) references ativo(id_ativo)
);

insert into tipo_ativo values(1, 'Fundos imobiliários');
insert into tipo_ativo values(2, 'Ações');