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
--오라클 날짜 함수 확인 , dual 테이블은 오라클 설치시 설치되는 임시테이블로 오라클 함수나 연산 겨로가값 확인 용도
select sysdate from dual;
/*
 * primary key : 중복 불가
 * not null : 중복 허용
 * date: 날짜 타입
 * 
 * */


--tbl_board 게시판 테이블 생성
create table tbl_board(
	bno number(38) primary key -- number38은 오라클 정수 숫자 타입으로 최대 38자 까지 저장 가능
	,writer varchar2(100) not null -- 작성자
	,title varchar2(200) not null--게시판 제목
	,content varchar2(4000)--내용 
	,viewcnt number(38) default 0-- 조회수 , 컬럼에 데이터 저장하지 않으면 0이 저장됨
	,regdate date --등록 날짜
	);
	select * from tbl_board order by bno;
	insert into TBL_BOARD values(bno_seq,'test','test','test',0,sysdate());
	delete from tbl_board;
	/*
	 * 시퀀스 : 번호 발생기  : 게시판 게시물 번호용도
	 * 현재 이후 번호값만 발생 - 중복 번호 x , 
	 * 
	 */
	create sequence bno_seq 
	start with 1 --1부터 시작하자
	increment by 1
	nocache; --임시 메모리를 사용하지 않겠다
	--시퀀스 이름.nextval로 다음 시퀀스 번호 확인
	select bno_seq.nextval from dual;
	
	
	