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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 애플리케이션의 설정정보, 구성정보를 적는 곳에 이 어노테이션을 붙힘
public class AppConfig { // 앱의 기획자 역활, 의존관계 주입을 할 수 있다.
    // 역할과 책임을 적절하게 분리하는데 사용했음!

    @Bean // 스프링 컨테이너에 담는다. 기본적인 이름은 기존 이름으로 들어감.
    public MemberService memberService() {
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(getMemberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    // Ctrl + E - 과거 히스토리 다 보여줌
}
