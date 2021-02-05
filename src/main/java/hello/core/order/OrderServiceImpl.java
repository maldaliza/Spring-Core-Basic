package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    // 생성자 주입을 하면 final 키워드 사용가능! (생성할 때 정해지면 바뀌지 않는다.)
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired      // 생성자가 딱 1개이므로 생략 가능! (생성자 주입)
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /*
        ### 롬복 라이브러리로 final 키워드(필수값)가 붙은 값을 파라미터로 받는 생성자를 자동으로 생성! ###

        @Autowired      // 생성자가 딱 1개이므로 생략 가능! (생성자 주입)
        public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
            this.memberRepository = memberRepository;
            this.discountPolicy = discountPolicy;
        }
    */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
