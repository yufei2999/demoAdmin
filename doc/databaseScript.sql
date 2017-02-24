drop table if exists sys_func;

drop table if exists sys_role;

drop table if exists sys_role_func;

drop table if exists sys_user;

drop table if exists sys_user_role;

/*==============================================================*/
/* Table: sys_func                                              */
/*==============================================================*/
create table sys_func
(
   id                   varchar(32) not null comment '主键',
   func_name            varchar(50) not null comment '权限名称',
   func_code            varchar(200) not null comment '权限code',
   url                  varchar(200) not null comment '权限url',
   parent_id            varchar(32) not null comment '父权限id',
   parent_ids           varchar(500) not null comment '父权限id全路径',
   sort                 int not null comment '排序',
   remark               text comment '备注',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime comment '创建时间',
   last_modifier        varchar(32) comment '最后修改人',
   last_modify_time     datetime comment '最后修改时间',
   deleted              int not null comment '删除标记：0删除；1正常',
   primary key (id)
);

alter table sys_func comment '系统权限表';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   varchar(32) not null comment '主键',
   role_name            varchar(50) not null comment '角色名称',
   remark               text not null comment '备注',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime comment '创建时间',
   last_modifier        varchar(32) comment '最后修改人',
   last_modify_time     datetime comment '最后修改时间',
   deleted              int not null comment '删除标记：0删除；1正常',
   primary key (id)
);

alter table sys_role comment '系统角色表';

/*==============================================================*/
/* Table: sys_role_func                                         */
/*==============================================================*/
create table sys_role_func
(
   id                   varchar(32) not null comment '主键',
   role_id              varchar(32) not null comment '角色id',
   func_id              varchar(32) not null comment '权限id',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime comment '创建时间',
   last_modifier        varchar(32) comment '最后修改人',
   last_modify_time     datetime comment '最后修改时间',
   deleted              int not null comment '删除标记：0删除；1正常',
   primary key (id)
);

alter table sys_role_func comment '角色权限关联表';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   varchar(32) not null comment '主键',
   user_name            varchar(50) not null comment '用户名',
   show_name            varchar(100) not null comment '显示名',
   password             varchar(100) not null comment '密码',
   mobile               varchar(20) comment '手机号',
   status               int comment '状态：0停用；1启用',
   last_login_ip        varchar(20) comment '最后登录ip',
   last_login_time      datetime comment '最后登录时间',
   remark               text comment '备注',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime comment '创建时间',
   last_modifier        varchar(32) comment '最后修改人',
   last_modify_time     datetime comment '最后修改时间',
   deleted              int not null comment '删除标记：0删除；1正常',
   primary key (id)
);

alter table sys_user comment '系统用户表';

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   id                   varchar(32) not null comment '主键',
   user_id              varchar(32) not null comment '用户id',
   role_id              varchar(32) not null comment '角色id',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime comment '创建时间',
   last_modifier        varchar(32) comment '最后修改人',
   last_modify_time     datetime comment '最后修改时间',
   deleted              int not null comment '删除标记：0删除；1正常',
   primary key (id)
);

alter table sys_user_role comment '用户角色关联表';
