create table opn (
      id bigint not null auto_increment,
      opn varchar(50), primary key (id)) engine=MyISAM;

create table param_scheme (
      id bigint not null auto_increment,
      colibr_ball integer not null,
      colibr_proc integer not null,
      name_sheme varchar(20),
      primary key (id)) engine=MyISAM;