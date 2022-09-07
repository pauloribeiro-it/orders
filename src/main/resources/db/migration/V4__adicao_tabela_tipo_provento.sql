create table tipo_provento(
	id_tipo_provento int not null,
	desc_tipo_provento varchar(30) not null,
	constraint pk_tipo_provento primary key(id_tipo_provento)
);

insert into tipo_provento(id_tipo_provento, desc_tipo_provento)
        values (1,'Rendimento'),
               (2, 'Dividendo'),
               (3, 'Juros sobre capital próprio');


alter table provento add tipo_provento int not null;
alter table provento add constraint fk_provento_tipo_provento foreign key(tipo_provento) references tipo_provento(id_tipo_provento);