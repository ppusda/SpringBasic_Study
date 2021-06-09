package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService = " + memberService1);
        System.out.println("memberService = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    } // 이렇게 하면 요청이 들어올 때 마다 객체가 생성이 된다.
    // 정말 비효율적일 텐데, 이를 해결 할 방법은 구현체를 한 개만 생성시키고 공유를 하면 된다 = 싱글톤 패턴

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isEqualTo(singletonService2);
        // same, 메모리를 비교
        // equal, 값 자체를 비교
    }
    // 이렇게 싱글톤 패턴을 사용할 수 있다.
    // 하지만 스프링 컨테이너에서는 싱글톤을 직접 관리해주는 역할을 한다.
}
