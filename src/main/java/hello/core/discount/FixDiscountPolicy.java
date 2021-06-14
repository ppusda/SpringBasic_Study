package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

// @Component // 같은 타입의 스프링 빈이 2개가 되면 자동의존 관계주입에서 문제가 생긴다.
public class FixDiscountPolicy implements DiscountPolicy{

    private int dicountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return dicountFixAmount;
        } else {
            return 0;
        }
    }
}
// 해결법을 알아보자.
