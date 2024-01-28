package kr.co.chunjae.controller;

import kr.co.chunjae.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//JSP와 같은 뷰를 반환하는 것이 아닌 JSON, XML 같은 데이터를 브라우저로 전송하는 @RestController를 설정
@Log4j2
@RestController
@RequestMapping("/test/*") // /test 밑의 모든 것
public class TestController {
    // /hello로 요청시 브라우저로 문자열을 전송하겠다
    // http://localhost:8092/test/hello
    @RequestMapping("/hello")
    public String hello() {
        return "Hello REST!!"; // 문자열이 표시됨
    }

    // MemberVO 객체의 속성 값을 저장한 후 JSON으로 전송할 것이다
    // http://localhost:8092/test/member
    // Content-Type: application/json
    @RequestMapping("/member")
    public MemberVO member() {
        MemberVO vo = new MemberVO();
        vo.setId("lee");
        vo.setPwd("1234");
        vo.setName("이자바");
        vo.setEmail("java@test.com");
        return vo;
    }

    // @RestController로 컬렉션 객체 전달
    // 컬렉션(list)로 전달해서 JSON배열로(배열 요소는 vo의 자료)
    // http://localhost:8092/test/memberList
    // Content-Type: application/json
    @RequestMapping("/memberList")
    public List<MemberVO> listMembers() {
        //Member 객체를 저장할 ArrayList 객체를 생성
        List<MemberVO> list = new ArrayList<>();
        //MemberVO객체를 10개 생성해서 ArrayList에 저장
        for (int i = 0; i < 10; i++) {
            MemberVO vo = new MemberVO();
            vo.setId("lee" + i);
            vo.setPwd("1234" + i);
            vo.setName("이자바" + i);
            vo.setEmail("java" + i + "@test.com");
            list.add(vo);
        }
        //ArrayList를 JSON으로 브라우저에 전송
        return list;
    }

    // @RestController로 Map 전달
    // http://localhost:8092/test/memberMap
    // Content-Type: application/json
    @RequestMapping("/memberMap")
    public Map<Integer, MemberVO> memberMap() {
        // MemberVO 객체를 저장할 HashMap 객체 생성
        Map<Integer, MemberVO> map = new HashMap<>();
        // MemberVO 객체를 HashMap에 저장
        for (int i = 0; i < 10; i++) {
            MemberVO vo = new MemberVO();
            vo.setId("lee" + i);
            vo.setPwd("1234" + i);
            vo.setName("이자바" + i);
            vo.setEmail("java" + i + "@test.com");
            map.put(i, vo);
        }
        return map;
    }

    // @PathVariable 사용 : 브라우저에서 요청 URL로 전달된 매개변수를 가져올 수 있음
    // http://localhost:8092/test/notice/112
    // Content-Type: application/json
    @RequestMapping(value = "/notice/{num}", method = RequestMethod.GET)
    public int notice(@PathVariable("num") int num) throws Exception {
        return num;
    }

    // int -> String[출력시 String 형식으로 가서 text/html 형식이 됨]
    // http://localhost:8092/test/notice/112
    // Content-Type: text/html
    /*@RequestMapping(value = "/notice/{num}", method = RequestMethod.GET)
    public String notice(@PathVariable("num") String s) throws Exception{
        return s;
    }*/
    // @RequestBody사용 : 브라우저에 전달되는 json데이터를 객체로 자동 변환
    //static Logger logger = LoggerFactory.getLogger(TestController.class);
    // static Logger logger = LoggerFactory.getLogger("kr.co.chunjae.TestController");


    @RequestMapping(value = "/info", method = RequestMethod.POST)
    //json으로 전송된 데이터를 MemberVO 객체의 속성에 자동으로 설정
    public void modify(@RequestBody MemberVO vo) {
        log.info(vo.toString());
    }

    //
    @RequestMapping("/memberList2")
    public ResponseEntity<List<MemberVO>> listMember2() {
        List<MemberVO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MemberVO vo = new MemberVO();
            vo.setId("lee" + i);
            vo.setName("이자바" + i);
            vo.setPwd("2345" + i);
            vo.setEmail("lee" + i + "@test.com");
            list.add(vo);
        }
        // 오류코드 500으로 응답
        return new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // @RequestBody와 @ResponseBody  : HttpHeaders 클래스를 이용해서 responseEntity로 전송할 데이터의 종류와 한글 인코딩 설정
    @RequestMapping(value = "/res3")
    public ResponseEntity res3(){
        HttpHeaders responseHeaders = new HttpHeaders();
        // 전송할 데이터의 종류와 인코딩을 설정
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        // 전송할 자바스크립트 코드를 문자열로 작성
        String message = "<script>";
        message += " alert('새 회원을 등록하겠습니다.');";
        message += " location.href='/test/memberList2';";
        message += "</script>";
        // ResponseEntity를 이용해 Html 형식으로 전송
        return new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
    }
}
