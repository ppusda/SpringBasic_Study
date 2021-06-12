package hello.core; // basePackages 의 default
                    // 자신의 기본위치가 default 이기 때문에 프로젝트 시작위치에 두는 것이 좋다.

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // basePackages = "hello.core" // 패키지 지정 가능, 여러개도 가능하다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 다른 appconfig 를 제외하기 위해서 configuration 을 제외함
)
public class AutoAppConfig {
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    // 겹치는 이름 수동으로 bean 등록...
    // Spring에서는 수동 빈이 우선권을 가지므로 오류가 나지는 않는다.
    // 하지만 이것은 명확한 내용이 아니며, 의도한 것이 아닐 수 있다. => 버그로 이어질 수 있음
    // 그렇기 Spring boot에서는 해당 내용이 충돌이 일어나게 해두었고, 강제로 풀 수는 있지만 권장하지는 않음
}
