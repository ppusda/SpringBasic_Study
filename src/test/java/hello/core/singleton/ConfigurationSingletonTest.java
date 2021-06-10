package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = memberService.getMemberRepository();
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("memberService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);


        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean);
        // hello.core.AppConfig$$EnhancerBySpringCGLIB$$bce49a9@3b8f0a79
        // 스프링 자체에서 바이트코드 조작 라이브러리를 사용해 appconfig를 상속받은 임의의 다른 클래스를 만들고,
        // 그 다른 클래스를 스르링 빈으로 등록한 것.
        // => 이 클래스 내에서 싱글톤이 보장될 수 있도록 코드가 작성되는 듯
    }
}
