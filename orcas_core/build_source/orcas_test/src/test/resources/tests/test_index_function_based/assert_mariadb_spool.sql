drop index MOD_1_IX on TAB_INDEX;
create index ADD_1_IX on TAB_INDEX ( NVL(COL_1,1) );
create index ADD_2_IX on TAB_INDEX ( COL_1,COL_2,NVL(COL_1,1) );
create index ADD_3_IX on TAB_INDEX ( NVL(COL_1,1),COL_2 );
create index ADD_4_IX on TAB_INDEX ( NVL(TO_CHAR(COL_1),'test') );
create index MOD_1_IX on TAB_INDEX ( NVL(TO_CHAR(COL_2),'klein') );
drop index INLINE_MOD_1_IX on TAB_INDEX_INLINE;
create index INLINE_ADD_1_IX on TAB_INDEX_INLINE ( NVL(COL_1,1) );
create index INLINE_ADD_2_IX on TAB_INDEX_INLINE ( COL_1,COL_2,NVL(COL_1,1) );
create index INLINE_ADD_3_IX on TAB_INDEX_INLINE ( NVL(COL_1,1),COL_2 );
create index INLINE_ADD_4_IX on TAB_INDEX_INLINE ( NVL(TO_CHAR(COL_1),'test') );
create index INLINE_MOD_1_IX on TAB_INDEX_INLINE ( NVL(TO_CHAR(COL_2),'klein') );