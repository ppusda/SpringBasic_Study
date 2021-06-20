package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean protoypeBean1 = ac.getBean(PrototypeBean.class);
        protoypeBean1.addCount();
        assertThat(protoypeBean1.getCount()).isEqualTo(1);

        PrototypeBean protoypeBean2 = ac.getBean(PrototypeBean.class);
        protoypeBean2.addCount();
        assertThat(protoypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUserPrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    // @RequiredArgsConstructor
    static class ClientBean {
        // private  final PrototypeBean prototypeBean; // 생성시점에 주입
        // ApplicationContext applicationContext;

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;
        // ObjectFactory는 ObjectProvider의 상위 인터페이스
        // DL(Dependency Lookup) 기능 제공
        // gradle 에 추가한 javax.inject의 provider도 같은 형식으로 작동된다. (자바 표준임)

//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        public int logic() {
            // PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
            // 로직을 실행 할 때마다(prototypeBean을 사용할 때 마다),
            // applicationContext에(SpringContainer에) 해로 요청하는 방법...
            // 이는 너무 스프링에 의존적이므로 좋은 코드가 아니다.

            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // 찾아주는 기능을 제공

            prototypeBean.addCount();
            int count = prototypeBean.getCount(); // Ctrl + alt + n // 리턴 리팩토링
            return count;

        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("Prototype.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy" + this);
        }
    }
}

// prototype을 새로 만들어서 쓰고싶은데 singleton 안에 생성되었기에(생성시점 후 관리 x)
// 그게 안되는 상황이 되버린 경우가 되었다.
