package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        // 기존 appconfig를 통해 의존성 주입을 해주었던 코드
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        // 스프링 컨테이너에 객체를 스프링 빈으로 등록하고, 스프링 컨테이너 에서 빈을 찾아서 사용하도록 한다.
        // 어떤 장점이 있을까??? 제어의 역전, 의존성 주입을 스프링이 대신 해줌으로써 편해짐일까??

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 38000);
        System.out.println("order = " + order);
        System.out.println("order = " + order.calculatePrice());
    }
}
