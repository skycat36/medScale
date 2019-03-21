create table client (
     id bigint not null,
     birthdate date not null,
     crib2 integer,
     date_of_arrival date not null,
     date_of_death date,
     date_of_departure date,
     fam varchar(50),
     name varchar(50),
     ntiss integer,
     opn varchar(50),
     pcs integer,
     sec_name varchar(50),
     snap_pe integer,
     sofa integer,
     survay_date date,
     trips integer,
     primary key (id)
     ) engine=MyISAM;

create table worker (
    id bigint not null,
    fam varchar(50),
    login varchar(50) not null,
    name varchar(50),
    password varchar(50),
    position varchar(100),
    sec_name varchar(50),
    primary key (id)
    ) engine=MyISAM;
alter table worker add constraint worker_fk unique (login);
