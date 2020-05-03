# ex00
스프링 프레임워크에 대해 기초적인 공부를 진행했습니다. 
기본적인 스프링의 특징에 대해 공부했고, 겸해서 안드로이드 스튜디오를 통한 서버와의 연동도 같이 공부했습니다.

최종 기능 구현 소개
1)회원 가입

2)로그인

3)게시판 글 쓰기

4)댓글 달기

5)파일 업로드 / 다운로드

6)pw 암호화

최종 사용 기술
1)java-spring-framework

2)ajax

3)sql




//- 공부 시작
home에서는 sqlplus day - 1234

설정 :
jdk : 1.8 - pom.xml에서 강제설정

처음 이클립스로 설치할때
 market -> sts -> spring tools 3 설치 -> 다 체크 후 confirm
epp221.exe=> 메모장 같은 개발툴 
평가하기 -> 확인? -> 문서=-> 영구 설정 -> 글꼴=consolas / 파일 -> 저장시 백업 파일 생성 체크 헤제-> 백업 디렉토리에 원격~~생성 체크해제

이클립스에    spring legach project 만들꺼임

오라클 접속 사용자 만들기 :
[Oracle 11g] 오라클 유저 system, sys 암호 변경


실 db저장 장소 C:\oraclexe\app\oracle\oradata\XE

 이클립스->openperspective->databaseconnection ->new ->oracle->next->
new drive definition -> systemversion 11->jarlist->ojdbc14.jar -> 
editjarjip ->ojdbc67.jar찾아추가 -> 
username,password입력 ,sid에 xe입력 , host에 127.0.0.1 입력 (server라고 일단 적혀 있음) , save password체크 


톰캣 설치 :
tomcat9클릭 ->core의 zip클릭 -> 워크스페이스에 압축풀기 -> 그후 이클립스 
->openperspective -> spring -> server에 뭐시기 클릭 -> 아파치 -> 톰캣 9 -> 워크스페이스 브라우즈 -> finish

포트번호 바꿈 :

 --><Server port="8051" shutdown="SHUTDOWN">
아파치 :8051

    -->
    <Connector connectionTimeout="20000" port="8052" protocol="HTTP/1.1" redirectPort="8443"/>
8052 

서버구동 완료

file->new->spring legacy project-> 이름짓고->template의 spring mvc project선택 -> next->페키지 명을 넣어야함 : org.zerock.controller써넣기->finish
프로젝트 생성 끝

파일 탐색기-> 사용자 -> 에가면 .m2폴더가 생성됨->repository가 있을것
만약 설치가 잘 안됬으면 repository지우고 이클립스 시작하면 되는것 -----

현 웹 주소 http://localhost:8052/controller/
패키지 명의 끝이 노출

</version>과 <property>사이에


	<repositories>
		<repository>
		 <id>oracle</id>
		 <url>http://maven.jahia.org/maven2</url>
		</repository>
	   </repositories>
이거 추가!!!!

저장 -> 폴더 ->메이븐 ->업데이트!

run as server -> hell world나오면 성공
--------------------------------------------------------------------------



jdk1.8로 바꼈는지 확인 :
file -> property -> javaCompiler :확인가능
		 -> project facets : java 1.8인지 확인가능

가. src/main/java : 패키지 이하 원본 자바파일/ vo,dao
나. src/main/resources : mybatis 관련 xml파일 경로(db)
다. src/test/java : 자바 테스트 경로
라. src/main/webapp/resources : html,css,javascript,jQuery,이미지 등 리소스 자원
경로
마. src/main/webapp/WEB-INF/spring : 스프링 관련 중요 설정파일
바. pom.xml : 메이븐 설정 파일


org.zerock.controller 패키지를 작성했다면 중간 경로가
 /controller이 된다. 최초 실행 경로는 브라우저에서
http://localhost:8081/controller가 된다.


junit test파일 만들기:
src/test/java밑에 org.~controller에ㅐ서 new ->class만들기

실행할때 run as junit test

mybatis :
xml 을 사용하여 sql문을 적용


namespace추가 
src->main->webapp->web-INF->spring->root-context.xml:
bean추가 하는 곳
여기서 밑의 namespace텝 누르기 -> aop,beabs,context,jdbc,mybatis,tx - 6개체크
->source텝 가면 추가되어있음


--------------------------------------------------------------------------


http://localhost:8052/controller/doA주소로 들어가면 controller실행

RedirectAttributes rttr
rttr.addFlashAttribute("msg","this is seven");
	
@ModelAttribute("msg") String name
System.out.println("전달된 값 :"+name);

db연결
우측 상단database development -> connect
resources 폴더에 SQL폴더 생성 , 
new ->other->sqlfile->이름정하고 생성 

sql 파일내에서...
타임 = oracle_11 이름 : new oracle    db:xe   이거 해야 사용가능


member.xml : 쿼리 두는 곳
mybatis-config.xml : 경로에 따른 별칭 지정
root-context.xml : 빈을 활용하는 의존성 주입 
web.xml : 
root-context.xml {
id dataSource : db 연결
sqlSessionFactory : ref=dataSource에 접근
sqlSession : ref=sqlSessionFactory에 접근하면서, 쿼리를 다루는 애
 db
/
dao(Model): 인터페이스가 필수		
  \
service(고객의 추가 요구사항)	
    	\
 		Controller 	--	 view(webinf/view경로에 .jsp확장자로)

public String doC(@ModelAttribute("msg") String message) {
		//@modelattribute("msg") : msg파라미터 이름에 인자값을 문자열로 전달한다
		
public String nameprice(Model m) {
m.addAttribute("a",a); // 프론트단에서 사용 가능


주소 변경 목적으로 넘기기
	@RequestMapping("/doE")
	public String doE(RedirectAttributes rttr) {
		rttr.addFlashAttribute("msg","this is seven");

		System.out.println(rttr.getFlashAttributes());
		//msg에 담기, 서버단의 다른 주소로 전달! => 보안성 우수
		return "redirect:/doF"; // doE => doF메핑 주소로 전달 = 프론트가 아님~!~!!!
		
	}
	@RequestMapping("/doF")
	public void doF(@ModelAttribute("msg") String name) {
		// void형이면 메핑주소가 jsp파일명이 된다
		//@modelattribute : 서버단 <> model : 프론트 단..???

		System.out.println("전달된 값 :"+name);
		
	}

daoimpl : 

@Autowired
	private SqlSession sqlSession;
	
this.sqlSession.insert("m_in",m);

<insert id="m_in" parameterType="m">
<!--  parameterType속성은 전달인자 타입으로 생략 가능 -->
insert into tbl_member values(#{userid},#{userpw},#{username},#{email},sysdate,sysdate)
<!--  sysdate : oracle날짜 함수 , #{userid}= m.getUserId() -->
</insert>

select 였다면 그냥 결과를 return 


cannot find class :  분명 있는데 없다고 뜨는 경우 :
 package explorer에서는 있지만 없다는 경우 임 -> 실제 경로로 들어가 봐야함
 실 경로 :
  [C: - 드라이브 ]\[workspace]\.metadata\.plugins\
 org.eclipse.wst.server.core\tmp0\wtpwebapps로 이동-프로젝트명-
 WEB-INF/lib폴더에 가면 pom.xml에서 추가한 라이브러리를 확인 가능
 하다.
 그리고 classes > org >zerock에 src/main이 존재
 그럼 여기에 아무것도 없기에 cannot find ! : 
이때 package exploere에 있는 아무거나 하나 java를 다른 폴더로 옮겼다가 원래대로 옮기면
나머지도 전부 실 경로에 추가되어 있음!


onclick="location='/controller/board/board_list';"
onsubmit="return check();">  submit버튼 누르면 check실행

function check(){
	if($.trim($("#writer").val())==""){
		// trim : 양쪽 공백 제거 -> writer의 value를 공백 제거했더니 ""라면!
		alert("글쓴이를 입력하세요!");
		$("#writer").val('').focus();//val('')입력박스값을 초기화 ,  focus는 입력박스로 포커스 이동
		return false;
		
	}
}

js 다운 : 
jquery.com -> down -> 
downliad the compressed ,production jquery 3.4.1
->이거 exploer에서 해야함
이름을 jquery.js라고 바꾸자 -> 만든폴더에 넣기

인자값 boardVO b   ,  int bno 줬을때 값이 들어가는데...
생략된것 : @ModelAttribute BoardVO b   -  model에서 불러와서 저장하는 듯
        @RequestParam("bno") int bno
		
ModelAndView는 Model m과 같은 기능
view 단에 input method 지정하면 controller패키지 안에 모든 경로를 탐색 - 때문에 유일해야함
		
		
dao.impl에서 id값 주고 mapper에 접근할때 
namespace.id로 갈수 있음!!
return this.sqlSession.selectOne("Board.b_count");
		==selectOne("b_count");
		
rest ?{
jsp파일을 만들지않고 json객체나 string으로 받아와서 사용
json : 키:값

}

ajax : 불필요한 화면 전환이 없다

advanced rest client 검색 -> 크롬에 추가 -> 앱추가{
일단 이거 실행시켜서 포스트 방식으로
url =http://127.0.0.1:8052/controller/replies/
{"bno":"27",
"replyer":"댓글작성자",
 "replytext":"댓글내용"
} - vo와 맞춰줘야함

http://127.0.0.1:8052/controller/replies/all/27.json
로 입력해보면 json 가져옴, 그냥 웹에서 출력도 가능

}

 requestbody : json을 객체로!
@PathVariable("rno") int rno

ResponseEntity<String> 
entity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
리턴으로 객체 이고 (제네릭 타입, 상태)리턴
			

url =http://127.0.0.1:8052/controller/replies/2
put에 두고
{
  "replytext":"수정"
}하면 바뀜....

삭제
delete 선택
url =http://127.0.0.1:8052/controller/replies/2

ajax 통신
	$('#replyAddBtn').on('click',function(){
		var replyer =$('#newReplyWriter').val();//value를 가져오는 것
		var replytext=$('#newReplyText').val();
		
		
		$.ajax({// ajax를 사용해보자 - get/post방식을 처리 , 많이 사용함!!!!! getjson보다 훨씬 많이 쓰나봐용....
			type:'post', // 서버를 자료를 보내는 형식
			url:'/controller/replies',
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"POST"
			},
			dataType:"text",
			data:JSON.stringify({
				bno:bno,//key:value쌍의 json데이터로 전송    지금 bno는 29라고 위에 지정해놨음
				replyer:replyer,
				replytext:replytext
			}),
			success:function(result){//ajax로 받아오는 것이 성공되면 호출되는 콜백 함수, 받아온 자료는 result 매게변수에 저장
				if(result=='success'){
					alert('댓글이 등록되었습니다!');
					getAllList();
				}
			}
		});		
	});
	

aspect oriented programming: 데이터 일관성을 유지

37   12과목 +1 
-13 = 24      4+1
15            5
9             3

트랜잭션 :함수내에 모든 동작이 성공했을때만 함수 내용 반영!
여러 sql문을 실행하는데 하나라도 틀리면 모든 것 rollback












