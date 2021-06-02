package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o(){
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니라면 할인이 적용되지 않아야한다.")
    void vip_x(){
        //given
        Member member = new Member(1L, "memberVIP", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(0); //static import 찾아보자
        // Ctrl + shift + t 테스트 생성 단축키
        // Assertions.AssertThat 에서 Alt + Enter 를 통해 onDemand static import를 실행해서 위 같이 만들었다.
        // 정리 예정

        // 그외의 단축키
        // Ctrl + Shift + Alt + T ---- 리팩토링 단축키
        // Ctrl + Shift + Enter ---- 빠른완성
        // Alt + Insert ---- generate 단축키
    }

}