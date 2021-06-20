package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
    // private final ObjectProvider<MyLogger> myLoggerProvider;
    // ObjectProvider 덕분에 아래에서 getObject를 실행하는 시점까지 request scope 빈의 생성을
    // 지연시켜서 오류를 해결 할 수 있었다.

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        //MyLogger myLogger = myLoggerProvider.getObject();

        System.out.println("myLogger = " + myLogger.getClass());
        // class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$5712ad75
        // 스프링이 조작한 CHLIB가 또 나옴, 가짜 프록시 객체를 만들어 주입을 해준 것
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK!";
    }
}
