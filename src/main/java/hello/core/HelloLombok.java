package hello.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("sss");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
} // 롬복 체험하기 / getter, setter 자동생성
