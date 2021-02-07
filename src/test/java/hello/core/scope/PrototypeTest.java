package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PrototypeBean.class);

        // 프로토타입 스코프의 빈은 스프링 컨테이너에서 빈을 조회하기 직전에 생성
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = applicationContext.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = applicationContext.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // 싱글톤 빈은 스프링 컨테이너가 관리해주므로 스프링 컨테이너가 종료될 때 종료 메소드가 실행되지만,
        // 프로토타입 빈은 스프링 컨테이너가 생성, 의존관계 주입, 초기화까지만 관여하고, 더는 관리하지 않는다.
        applicationContext.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destory() {
            System.out.println("PrototypeBean.destory");
        }
    }
}
