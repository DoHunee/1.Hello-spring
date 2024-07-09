package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component // 스프링 빈으로 등록 => 물론 SpringConfig에서도 직접 등록해도 된다~
@Aspect    // 이 클래스를 AOP로 사용함을 표시
public class TimeTraceAop {
  

  // 대상 메소드 지정: hello.hellospring 패키지 및 그 하위 패키지에 있는 모든 메소드
  // 여기서 @Around로 타켓팅을 해주는거야!!!!
  @Around("execution(* hello.hellospring..*(..))") 
  // @Around("execution(* hello.hellospring.service..*(..))") // 이런식으로 서비스만 보이게 할 수 있다!!


  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
  
    long start = System.currentTimeMillis(); // 시작 시간 측정
    System.out.println("START: " + joinPoint.toString()); // 시작 로그
  
    try {
      return joinPoint.proceed(); // 어드바이스가 적용된 원래의 메소드를 실행합니다.
    } 
    finally {
      long finish = System.currentTimeMillis(); // 종료 시간 측정
      long timeMs = finish - start; // 실행 시간 계산

      System.out.println("END: " + joinPoint.toString() + " " + timeMs +"ms"); // 종료 로그
    }

  }

}