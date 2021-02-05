package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan (        // @Component 애노테이션이 붙은 클래스를 찾아서 자동으로 스프링 빈에 등록.
        // 자동으로 스프링 빈을 등록하는 도중에 뺄 것을 지정하는 것.
        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    /*
        // 예시를 보여주기 위해 임의로 생성한 스프링 빈
        @Bean(name = "memoryMemberRepository")
        MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }
    */
}
