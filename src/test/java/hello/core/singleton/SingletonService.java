package hello.core.singleton;

public class SingletonService {

//    private static final SingletonService instance = new SingletonService();
//    // static으로 생성해서 클래스영역(Static 영역)에 하나만 만들어두고
//
//    public static SingletonService getInstance() {
//        return instance;
//    } // 해당 메소드를 통해서만 조회 할 수 있고,
    // 이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.

    private SingletonService() { } // private 외부 생성 막기

    private static class InnerInstanceClass {
        private static final SingletonService instance = new SingletonService();
    }

    public static SingletonService getInstance() {
        return InnerInstanceClass.instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

    // 객체를 미리 생성해두는 가장 단순하고 안전한 방법
    // 다른 방법도 있다.
}

// 싱글톤의 문제점들도 있다.
/*
   싱글톤 패턴을 구현하는 코드가 필요하다.
   의존관계상 클라이언트가 구체 클래스에 의존한다 = DIP 위반
   OCP를 위반할 가능성이 있다.
   테스트하기 힘들다.
   유연성이 떨어진다
   private 이기에 자식 클래스를 만들기 어렵다
   안티패턴으로 불리기도 한다.
 */
