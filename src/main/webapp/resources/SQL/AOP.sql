create table tbl_user(
uid2 varchar2(50) primary key, --회원 아이디
upw varchar2(50) not null, --비번
uname varchar2(100) not null,--회원 이름
upoint number(38) default 0 --메시지가 보내지면 포인트 10점 업
);
select * from tbl_user;
select * from TBL_MESSAGE;
delete from tbl_message where mid=3;


insert into tbl_user(uid2,upw,uname) values('user00','user00','홍길동');
insert into tbl_user(uid2,upw,uname) values('user01','user01','이순신');

create table tbl_message(
mid number(38) primary key,
targetid varchar2(50) not null,--외래키 제약조건으로 추가설정 =>tbl_user테이블의 uid컬럼 회원 아이디만 저장가능
sender varchar2(50) not null,--메시지 보낸사람
message varchar2(1000) not null,--보낸 메시지
senddate date
);

alter table tbl_message add constraint fk_usertarget
foreign key(targetid) references tbl_user(uid2);
-- 외래키 targetid가 주인키 칼럼인 tbl_user테이블의 uid2컬럼을 참조하고 있다.

create sequence mid_no_seq
start with 1
increment by 1
nocache; 
select mid_no_seq.nextval from dual;














