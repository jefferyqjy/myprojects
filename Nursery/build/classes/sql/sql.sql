drop table if exists PRO_USER;
create table PRO_USER(
user_name varchar(15) not null,
user_password varchar(20) not null,
PRIMARY KEY (user_name )
);
insert into pro_user values('admin','admin');
insert into pro_user values('teacher','admin');
insert into pro_user values('family','admin');

drop table if exists PRO_USER_ROLE;
CREATE TABLE PRO_USER_ROLE (
user_name VARCHAR( 15 ) NOT NULL ,
role_name VARCHAR( 15 ) NOT NULL ,
PRIMARY KEY (user_name, role_name)
);

insert into pro_user_role values ('admin','admin');
insert into pro_user_role values ('teacher','teacher');
insert into pro_user_role values ('family','family');

drop table if exists PRO_TEACHER;
create table PRO_TEACHER(
user_name varchar(15) primary key,
name varchar(20) not null,
age int(3) not null,
subject int(3) not null,
sex varchar(2) not null,

constraint fk_user_name foreign key(user_name) references PRO_USER(user_name),
constraint fk_subject foreign key(subject) references PRO_SUBJECT(id)
);

drop table if exists pro_student;
create table pro_student(
bodycard varchar(18) primary key,
name varchar(10) not null,
birthday datetime not null,
sex varchar(2) not null,
nationality varchar(10) default '∫∫',
nativeplace varchar(10) ,
school varchar(50) not null,
in_date datetime,
out_date datetime,
father_name varchar(10),
father_work_address varchar(100),
mother_name varchar(10),
mother_work_address varchar(100),
telephone varchar(20),
description varchar(1024)
);

drop table if exists  student_health;
create table student_health(
id int(8) identity(10000,1)  primary key ,
check_date datetime not null,
bodycard varchar(18) not null,
height double(5,2) not null,
age int(2) not null,
weight double(5,2) not null,
left_sight double(3,1),
right_sight double(3,1),
description varchar(1024)
)

create table student_health(
id int(8)  primary key auto_increment,
check_date datetime not null,
bodycard varchar(18) not null,
height double(5,2) not null,
age int(2) not null,
weight double(5,2) not null,
left_sight double(3,1),
right_sight double(3,1),
description varchar(1024)
)AUTO_INCREMENT = 10000;

select A.name, A.bodycard , B.height from (select * from pro_student where name like '%’‘%') A left join student_health B on A.bodycard = B.bodycard order by A.bodycard;

select count(*) totals from (select * from pro_student where name like '%’‘%') A left join student_health B on A.bodycard = B.bodycard order by A.bodycard;



select * from (select * from pro_student ) A left join student_health B on A.bodycard = B.bodycard order by A.bodycard;

select A.id, A.bodycard, A.height, A.weight, A.age, A.check_date, A.left_sight, A.right_sight, A.description, B.name, B.sex from student_health A, pro_student B where A.bodycard = B.bodycard order by A.check_date desc;










