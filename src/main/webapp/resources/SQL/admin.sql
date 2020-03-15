--admin 관리자 테이블 생성
create table admin(
	admin_no number(38),
	admin_id varchar2(38) primary key,
	admin_pwd varchar2(50) not null,
	admin_name varchar2(50) not null,
	admind_date date
	
);
alter table admin rename column admind_date to admin_date;

select * from admin;

select admin_no_seq.nextval from dual;

create sequence admin_no_seq
start with 1
increment by 1
nocache;
insert into admin values(admin_no_seq.nextval,'admin','1234','관리자',sysdate);