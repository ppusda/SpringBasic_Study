package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 애플리케이션의 설정정보, 구성정보를 적는 곳에 이 어노테이션을 붙힘
// Configuration 을 제거한다면 싱글톤이 보장되지 않음
public class AppConfig { // 앱의 기획자 역활, 의존관계 주입을 할 수 있다.
    // 역할과 책임을 적절하게 분리하는데 사용했음!

    @Bean // 스프링 컨테이너에 담는다. 기본적인 이름은 기존 이름으로 들어감.
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

//    @Bean
//    public MemberRepository getMemberRepository() {
//        return new MemoryMemberRepository();
//    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    // Ctrl + E - 과거 히스토리 다 보여줌
    // @Bean 만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지는 않는다.
    // @Configuration 을 통해 싱글톤을 보장해야한다.
}
