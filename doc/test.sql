
-- mysql
CREATE TABLE `test`.`test`(
  `TEST_ID` VARCHAR(32) NOT NULL,
  `CONTENT` VARCHAR(4000),
  `CREATE_TIME` DATETIME,
  PRIMARY KEY (`TEST_ID`)
) CHARSET=utf8;

insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (1, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (2, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (3, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (4, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (5, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (6, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (7, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (8, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (9, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (10, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (11, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (12, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (13, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (14, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (15, '测试内容', NOW());
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (16, '测试内容', NOW());
commit;



-- oracle
-- Create table
create table TEST
(
  TEST_ID     NUMBER not null,
  CONTENT     VARCHAR2(1000),
  CREATE_TIME DATE,
  constraint PK_TEST primary key (TEST_ID)
);
-- Add comments to the columns 
comment on column TEST.TEST_ID
  is '主键id';
comment on column TEST.CONTENT
  is '名称';
comment on column TEST.CREATE_TIME
  is '创建时间';

-- Create sequence 
create sequence SEQ_YUFEI_ID
minvalue 1
maxvalue 999999999999999
start with 101
increment by 1
nocache
cycle;

--init
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
insert into TEST (TEST_ID, CONTENT, CREATE_TIME) values (SEQ_YUFEI_ID.nextval, '测试内容', sysdate);
commit;
