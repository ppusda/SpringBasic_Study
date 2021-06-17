package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClient networkClient() { // 빈 소멸 전 콜백
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}

// 빈에 설정정보를 작성, @Bean(initMethod = "init", destroyMethod = "close")
// 메서드 이름을 자유롭게 줄 수 있다.
// 스프링 빈이 스프링 코드에 의존하지 않는다.
// 코드가 아니라 설정 정보를 사용하기에 코드를 고칠 수 없는 외부 라이브러리에도 이용할 수 있다!!

// 종료 메서드 추론기능 - 종료 이름을 추론해 스프링 자체적으로 종료시킨다. (만약 하기 싫다면 위에 close부분을 공백으로 하자 ㅋㅋ)
// 그렇기에 스프링 빈으로 등록하면 기본적으로 종료시키는 기능이 존재하기는 한다.

