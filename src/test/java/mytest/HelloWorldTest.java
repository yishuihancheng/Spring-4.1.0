package mytest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/9/18.
 */
public class HelloWorldTest {
    public static void main(String args[]){
        ApplicationContext context = new ClassPathXmlApplicationContext("my-spring.xml");
        HelloApi helloWorld = (HelloApi) context.getBean("hello");
        helloWorld.sayHello();
        HelloApi myHelloWorld = (HelloApi) context.getBean("myHelloWorld");
        myHelloWorld.sayHello();
    }
}
