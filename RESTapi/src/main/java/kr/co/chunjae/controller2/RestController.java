package kr.co.chunjae.controller2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
// @ResponseBody 사용 : JSP가 아닌 텍스트나 json으로 결과를 전송
//http://localhost:8092/res1
@Controller
public class RestController {
    @RequestMapping(value = "/res1")
    // 메서도 호출시 데이터를 전송하도록 설정
    @ResponseBody
    // Map데이터를 브라우저로 전송
    public Map<String, Object> res1() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "lee");
        map.put("name", "이자바");
        return map;

    }

    //http://localhost:8092/res2
    // 메서드 호출시 home.jsp를 브라우저로 전송
    @RequestMapping("/res2")
    public ModelAndView res2(){
        return new ModelAndView("home");
    }
}
