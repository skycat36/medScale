create table crib2 (
        id bigint not null auto_increment,
        primary key (id)
        ) engine=MyISAM;

create table ntiss (
        id bigint not null auto_increment,
        colibr_ball integer not null,
        colibr_proc integer not null,
        primary key (id)
        ) engine=MyISAM;

create table pcs (
        id bigint not null auto_increment,
        colibr_proc integer not null,
        primary key (id)
        ) engine=MyISAM;

create table snappe (
        id bigint not null auto_increment,
        colibr_ball integer not null,
        colibr_proc integer not null,
        primary key (id)
        ) engine=MyISAM;

create table sofa (
        id bigint not null auto_increment,
        colibr_ball integer not null,
        colibr_proc integer not null,
        primary key (id)
        ) engine=MyISAM;

create table trips (
        id bigint not null auto_increment,
        primary key (id)
        ) engine=MyISAM;