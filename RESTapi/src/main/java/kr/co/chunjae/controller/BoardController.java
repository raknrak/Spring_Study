package kr.co.chunjae.controller;

import kr.co.chunjae.vo.ArticleVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/boards")
public class BoardController {
    // Get 방식으로 요청하기 때문에 모든 글의 정보를 조회 -> 전체 글을 조회하기
    @GetMapping("/all")
    public ResponseEntity<ArticleVO> listArticleVO() {
        log.info("listArticles 메소드 호출");
        List<ArticleVO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArticleVO vo = new ArticleVO();
            vo.setArticleNo(i);
            vo.setWriter("홍길동" + i);
            vo.setTitle("테스트입니다" + i);
            vo.setContent("새 상품을 소개합니다" + i);
            list.add(vo);
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //Get 방식으로 요청하면서 글 번호를 전달하기 때문에, 글 번호에 대한 그 정보를 조회 -> 선택한 글에 대한 자료만 가져옴
    @GetMapping("/{articleNo}")
    public ResponseEntity<ArticleVO> findArticle(@PathVariable("articleNo") Integer articleNo) {
        log.info("findArticle 메서드 호출");
        ArticleVO vo = new ArticleVO();
        vo.setArticleNo(articleNo);
        vo.setWriter("이자바");
        vo.setTitle("테스트 중입니다~");
        vo.setContent("이자바의 글입니다");

        return new ResponseEntity(vo, HttpStatus.OK);
    }

    // Post방식으로 요청하기 때문에 요청이 들어오면 json으로 전달되는 객체를 새 글로 추가
    @PostMapping("")
    public ResponseEntity<String> addArticle(@RequestBody ArticleVO articleVO) {
        ResponseEntity<String> responseEntity = null;
        try {
            log.info("addArticle 호출");
            log.info(articleVO.toString());
            responseEntity = new ResponseEntity<String>("ADD_SUCCEEDED", HttpStatus.OK);

        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    // Put 방식으로 요청하기 때문에 articleNo에 대한 글을 JSON 방식으로 수정
    @PutMapping("/{articleNo}")
    public ResponseEntity<String> modArticle(
            @PathVariable("articleNo")Integer articleNo, @RequestBody ArticleVO articleVo) {
        ResponseEntity<String> responseEntity = null;
        try {
            log.info("modArticle 메소드 호출");
            log.info(articleVo.toString());
            responseEntity = new ResponseEntity<String>("MOD_SECCEEDED", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    // Delete 방식으로 요청하기 때문에 전달되는 articleNo에 대한 글을 삭제 가능
    @DeleteMapping("/{articleNo}")
    public ResponseEntity<String> removeArticle(@PathVariable("articleNo") Integer articleNo) {
        ResponseEntity<String> responseEntity = null;
        try {
            log.info("removeArticle 메서드 호출합니다");
            log.info(articleNo.toString());
            responseEntity = new ResponseEntity<String>("REMOVE_SECCEEDED", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

}
