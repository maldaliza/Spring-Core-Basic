package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /*
        test/ConfigurationSingletonTest.java의 configurationTest() 메소드를 실행하면

        ### 우리가 생각하는 호출 순서 ###
        call AppConfig.memberService
        call AppConfig.memberRepository
        call AppConfig.memberRepository
        call AppConfig.orderService
        call AppConfig.memberRepository

        ### 실제 콘솔에 찍히는 호출 순서 ###
        call AppConfig.memberService
        call AppConfig.memberRepository
        call AppConfig.orderService

        ### 이유 ###
        AppConfig@CGLIB라는 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고,
        그 다른 클래스를 스프링 빈에 등록한 것!
        @Bean이 붙은 메소드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환
        스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
    */

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
