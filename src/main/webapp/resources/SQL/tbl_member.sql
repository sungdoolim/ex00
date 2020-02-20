--한글 주석문
--tbl_member테이블 생성
create table tbl_member(
userid varchar2(50) primary key,
userpw varchar2(50) not null,
username varchar2(50) not null,
email varchar2(100),
regdate date,--등록날짜
updatedate date--수정날짜
);
select * from tbl_member;
/*
 * primary key : 중복 불가
 * not null : 중복 허용
 * date: 날짜 타입
 * 
 * */


