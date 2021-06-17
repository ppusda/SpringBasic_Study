package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient  {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url +  ", message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close = " + url);
    }

    @PostConstruct
    public void init() { // 의존관계 주입이 다끝나고 나면 호출
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() { // 빈이 종료될 때(소멸 전 호출)
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}

// 인터페이스 방식, implements InitializingBean, DisposableBean
// 해당 코드가 인터페이스에 의존하게 되고, 초기화 소멸 메서드의 이름을 변경할 수 없다...
// 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다..

// 어노테이션 방식 @PostConstruct, @PreDestroy
// init, close... 초기화와 소멸 할 때 처리하는 메소드에 붙혀서 써준다.
// 최신 스프링에서 가장 권장하는 방법이며, 자바 표준이기에 스프링이 아닌 다른 컨테이너에서도 동작한다.
// 컴포넌트 스캔과도 잘 어울린다.
// 유일한 단점은... 외부 라이브러리에는 적용하지 못한다는 것이다. 만약 필요하다면 전에 배웠던 빈 설정정보를 사용하면 된다.
// 결론! 이 방식을 사용하자!