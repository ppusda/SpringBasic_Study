package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // dip를 지키기 위해 인터페이스에만 의존

    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // -  이 코드로 바꿀 시에는 구체 클래스에도 의존을 하게 된다. 의존관계를 재설정해야한다.
    // 스프링을 통해 구현 클래스를 위의 객체에 주입해줘야 해결이 될 듯하다.
    // appconfig로 ---->


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemname, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemname, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
