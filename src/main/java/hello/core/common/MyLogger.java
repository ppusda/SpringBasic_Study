package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// proxyMode 속성을 추가해준다, 클래스면 Target_class, 인터페이스라면 interface로
// 가짜 프록시객체는 요청이 오면 그때 내부에서 진짜 빈을 요청하는 위임 로직이 들어있다.
// 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다는 것이 핵심이다.

// 마치 싱글톤 처럼 사용하지만, 다르기 때문에 주의해야 한다.
// 이런 특별한 scope는 최소한의 상황에서 필요한 곳에만 사용해야만 한다.

public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]" + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "]" + "] request scope bean close:" + this);
    }
}
