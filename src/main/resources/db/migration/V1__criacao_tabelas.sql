
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
	subscricao bit,
	constraint PK_ORDEM primary key(id_ordem),
	constraint FK_ORDEM_ATIVO foreign key (id_ativo) references ativo(id_ativo)
);

create table tipo_provento(
	id_tipo_provento int not null,
	desc_tipo_provento varchar(30) not null,
	constraint pk_tipo_provento primary key(id_tipo_provento)
);

create table provento(
	id_provento int auto_increment not null,
	data_provento date not null,
	valor decimal(14,2),
	id_ativo int not null,
	tipo_provento int not null,
	qtd int,
	total decimal(14,2) not null,
	constraint pk_provento primary key(id_provento),
	constraint fk_provento_ativo foreign key (id_ativo) references ativo(id_ativo),
	constraint fk_provento_tipo_provento foreign key(tipo_provento) references tipo_provento(id_tipo_provento)
);