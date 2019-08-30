create table tab_new
(  
  id  number(5),
  constraint tab_new_check_size check ("ID BETWEEN 1 AND 10000")
);
 
create table tab_chg
(
  id  number(5),
  id_add number (5),
  constraint tab_chg_check_size_keep check ("ID BETWEEN 1 AND 11"),
  constraint tab_chg_check_size check ("ID BETWEEN 1 AND 900"),
  constraint tab_chg_check_size_new check ("ID BETWEEN 1 AND 99"),
  constraint tab_chg_check_size_new_col check ("id_add BETWEEN 1 AND 99"),
  constraint tab_chg_check_size_new_col_not_null check ("id_add not null")
); 
 



