create table tab_ins_col
(
  id number(15) not null,
  chg_col_add_not_null number(15),
  chg_col_add_default number(15),
  chg_col_add_default_not_null number(15),
  chg_col_del_default number(15) default "55",
  chg_col_del_not_null number(15) not null,
  chg_col_inc_precision number(10),
  chg_col_inc_scale number(15,5)
);

create table tab_del_col
(
  id number(15) not null,
  col_default number(15) default "66",
  col_not_null number(15) not null,
  col_not_null_default number(15) default "66" not null
);

create table tab_del
(
  id number(15) not null,
  col_default number(15) default "66",
  col_not_null number(15) not null,
  col_not_null_default number(15) default "66" not null
);


