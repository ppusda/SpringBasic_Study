package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // 같은 타입의 스프링 빈이 2개가 되면 자동의존 관계주입에서 문제가 생긴다.
// @Qualifier("fixDiscountPolicy")
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
// 1. Autowired 필드 명 매칭
// 2. Qualifier -> Qualifier 끼리 매칭 -> 빈 이름 매칭
// 3. Primary 사용하기
// 사용해보자
