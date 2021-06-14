package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() { // 주입할 스프링 빈이 없어도 동작해야 한다... => 옵션처리
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        // 주입할 스프링 빈이 없을 때도 실행해야 할 때가 있다. => 옵션처리
        @Autowired(required = false) // 의존관계가 없다면 메소드 자체를 호출하지 않는다.
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) { // 호출은 되지만 null 값으로
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) { // 위와 동일이지만, Optional.empty 값으로
            System.out.println("noBean3 = " + noBean3);
        }
    }


}
