package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // ("orderservice") 로 이름 부여가능
@RequiredArgsConstructor // 아래 final로 생성된 필드를 자동으로 모아서 생성자로 만들어준다.
public class OrderServiceImpl implements OrderService{

    // @Autowired
    private final MemberRepository memberRepository;
    // @Autowired
    private final DiscountPolicy discountPolicy; // dip를 지키기 위해 인터페이스에만 의존
    // 필드 주입, 필드 앞에 바로 @Autowired를 넣는다.
    // 하지만 권장하지 않는 방법... 테스트코드 같은데서만 사용하고, 실제로는 사용하지 말자...


//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    } // 수정자 주입, final을 없애야 함, 변경, 선택적 의존관계 주입

    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // -  이 코드로 바꿀 시에는 구체 클래스에도 의존을 하게 된다. 의존관계를 재설정해야한다.
    // 스프링을 통해 구현 클래스를 위의 객체에 주입해줘야 해결이 될 듯하다.
    // appconfig로 ---->

//    // @Autowired // 생성자가 한개이면 생략가능하다.
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    } // 생성자 주입 방법, 필수 값이며 불변해야 하는 경우
//    // 최적화를 위해 롬복이라는 라이브러리를 사용해보자

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
// 롬복, 생성자 한개, 스프링의 component 까지 엄청난 최적화 가능
