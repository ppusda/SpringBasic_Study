package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
    // 1차 시도
    // setter 주입 이용, appconfig return null => nullpointerException
    // 주입하는 setter에 public 이용해야만 함

    // 2차 시도
    // 생성자 주입 사용
    // 필드에 final 사용가능 => 컴파일 오류로 혹시 모를 실수를 잡아낼 수 있다.

    // 생성자 주입을 사용하자!!
    // 프레임워크에 의존하지 않고, 순수한 자바언어의 특징을 잘 살리는 방버이다.
    // 기본으로 생성자 주입을 사용하고, 필수 값이 아닌 경우에는 수정자 주입 방식을 옵션으로 부여하면 된다...
    // 필드 주입은 존재만 알아두기 ㅋㅋ

}
