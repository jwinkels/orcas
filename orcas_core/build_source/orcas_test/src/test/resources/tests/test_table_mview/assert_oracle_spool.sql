drop materialized view MVIEW_MOD_SELECT;
drop materialized view MVIEW_MOD_BUILD_MODE;
drop materialized view MVIEW_MOD_TABSPACE;
drop materialized view MVIEW_MOD_TABSPACE_REVERSE;
create table TAB_ADD_MVIEW (  COL_ADD_IX_1 NUMBER(15) not null, COL_ADD_IX_2 NUMBER(15) not null   )  ;
create materialized view MVIEW_ADD_CLOB_VIEWSELECT as select col_add_ix_1, col_add_ix_2, rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_3,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_4,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_5,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_6,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_7,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_8,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_9,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_10,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_11,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_12,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_13,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_14,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_15,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_16,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_17,
rpad('111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 150, '1') as col_18
from tab_view;
create materialized view TAB_ADD_MVIEW on prebuilt table  refresh complete enable query rewrite as select col_add_ix_1, col_add_ix_2 from tab_view;
alter materialized view TAB_MOD_MVIEW never refresh;
alter materialized view TAB_MOD_REWRITE disable query rewrite;
create materialized view MVIEW_MOD_SELECT as select col_add_ix_1, col_add_ix_2 from tab_view;
create materialized view MVIEW_MOD_BUILD_MODE as select col_add_ix_1, col_add_ix_2 from tab_view;
alter materialized view MVIEW_MOD_COMPRESS compress;
alter materialized view MVIEW_MOD_PARALLEL parallel;
alter materialized view MVIEW_MOD_PARALLEL_4 parallel 4;
alter materialized view MVIEW_MOD_NOPARALLEL noparallel;
create materialized view MVIEW_MOD_TABSPACE tablespace SYSTEM as select col_add_ix_1, col_add_ix_2 from tab_view;
create materialized view MVIEW_MOD_TABSPACE_REVERSE tablespace USERS as select col_add_ix_1, col_add_ix_2 from tab_view;
create materialized view MVIEW_ADD_IMMEDIATE  refresh complete as select col_add_ix_1, col_add_ix_2 from tab_view;
create materialized view MVIEW_ADD_DEFERRED build deferred  refresh complete as select col_add_ix_1, col_add_ix_2 from tab_view;
create materialized view MVIEW_ADD_FORCE build deferred as select col_add_ix_1, col_add_ix_2 from tab_view;
create materialized view MVIEW_ADD_COMPRESS compress for all operations as select col_add_ix_1, col_add_ix_2 from tab_view;
create materialized view MVIEW_ADD_DEFAULT_TABSPACE tablespace USERS compress as select col_add_ix_1, col_add_ix_2 from tab_view;
create materialized view MVIEW_ADD_DOMAIN_TABSPACE tablespace SYSTEM compress as select col_add_ix_1, col_add_ix_2 from tab_view;
