package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {

        // 스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적인 조회 방법
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // 검증
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = applicationContext.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 별로 좋지 않은 코드(역할에 의존하지 않고, 구현에 의존 => 변경 시 유연성이 떨어진다.)
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberServiceImpl memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 실패 테스트
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        // applicationContext.getBean("xxxxx", MemberService.class);
        // MemberService xxxxx = applicationContext.getBean("xxxxx", MemberService.class);

        // 파리미터의 오른쪽 인자를 실행하면 왼쪽 인자가 발생해야한다. => 성공
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> applicationContext.getBean("xxxxx", MemberService.class));
    }
}
