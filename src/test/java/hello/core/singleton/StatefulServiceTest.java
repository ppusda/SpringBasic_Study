package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);

        //ThreadB : B사용자가 20000원 주문
        int userBPrice = statefulService1.order("userA", 20000);

        //ThreadA : A사용자가 주문 금액 조회
        // int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);
        // 기대값을 10000 이지만, 20000이 나와버린다... (싱글톤 때문에)
        // 특정 클라이언트에 의존적인 필드가 있어서는 안된다.
        // 가급적 읽기만 가능해야한다.
        // 필드 대신에 공유되지않는 지역변수, 파라미터, ThreadLocal을 사용해야한다.
        // => 스프링은 항상 무상태로 설계해야한다.

        // assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}