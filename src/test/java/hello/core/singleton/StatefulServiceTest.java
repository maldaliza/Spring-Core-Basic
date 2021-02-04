package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = applicationContext.getBean(StatefulService.class);
        StatefulService statefulService2 = applicationContext.getBean(StatefulService.class);

        // ThreadA: 사용자A 10000원 주문
        statefulService1.order("userA", 10000);

        // ThreadB: 사용자B 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자A가 주문 금액을 조회한다.
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);     // price = 20000 으로 출력된다.

        // 검증
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}