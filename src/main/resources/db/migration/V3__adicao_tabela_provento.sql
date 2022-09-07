create table provento(
	id_provento int auto_increment not null,
	data_provento date not null,
	valor float not null,
	id_ativo int not null,
	constraint pk_provento primary key(id_provento),
	constraint fk_provento_ativo foreign key (id_ativo) references ativo(id_ativo)
);
