<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--보내기 버튼을 클릭하면,Ajax를 이용해서 회원 정보를 JSON으로 만들어 컨트롤러로 보냄--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>JSONTest</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>

<input type="button" id="checkJson" value="회원 정보 보내기" /> <br><br>
<div id="output"></div>
</body>
<script>
    let contextPath = "${contextPath}";
    $(function() {
        $("#checkJson").click(function() {
            // 회원 정보를 json으로 생성
            let member = {
                id:"kim",
                name:"김연아",
                pwd:"1234",
                email:"kim@test.com"
            };
            $.ajax({
                type:"post",
                // /test/info 로 요청하기
                url: contextPath + "/test/info",
                contentType: "application/json",
                // 회원정보를 josn 문자열로 변환
                data :JSON.stringify(member),
                success:function (data,textStatus){
                },
                error:function(data,textStatus){
                    alert("에러가 발생했습니다.");
                },
                complete:function(data,textStatus){
                }
            });//end ajax
        });
    });
</script>
</html>