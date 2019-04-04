create table client (
    id bigint not null auto_increment,
    birthdate date,
    crib2 integer,
    date_of_arrival date,
    date_of_death date,
    date_of_departure date,
    fam varchar(50),
    gestation integer,
    name varchar(50),
    ntiss integer,
    opn bigint,
    pcs integer,
    sec_name varchar(50),
    sex varchar(255),
    snap_pe integer,
    sofa integer,
    survay_date date,
    trips integer,
    weight integer,
    primary key (id)) engine=MyISAM;

create table worker (
    id bigint not null auto_increment,
    fam varchar(50),
    login varchar(50) not null,
    name varchar(50),
    password varchar(50),
    position varchar(100),
    sec_name varchar(50),
    primary key (id)
    ) engine=MyISAM;
alter table worker add constraint worker_fk unique (login);
