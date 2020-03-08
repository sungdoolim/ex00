
	select * from tbl_board order by bno desc;
	select * from tbl_reply order by rno desc;
	create table tbl_reply(
	rno number(38) primary key -- 댓글 번호 , 최대 38자 제한
	,bno number(38) default 0 -- bno번호 레코드 값만 저장되게 외래키 제약조건을 설정할 것임
	,replyer varchar2(100) not null -- 댓글 작성자
	,replytext varchar2(4000) not null -- 댓글 내용
	,regdate date -- 댓글 등록 날짜, date는 오라클 날짜 타입
	,updatedate date-- 댓글 수정날짜
	
	);
--외래키 제약조건 설정
alter table tbl_reply add constraint fk_board foreign key(bno) references tbl_board(bno);
--reply 의 외래키 제약조건 칼럼인 bno 가 board의 bno를 참조한다는 뜻임 -- 참조무결성 제약조건에 의해 board테이블의 게시물 번호값만 외래키 제약조건 컬럼 번호값으로 저장할수 있다.
-- board에 존재해야 생성할수 있다는 뜻임


--번호발생기 만들기
create sequence rno_seq
start with 1
increment by 1
nocache;--임시메모리 사용 안함@!

select rno_seq.nextval from dual;
