package mytest;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/9/18.
 */
@Service
public class MyHelloWorld implements HelloApi{
    @Override
    public void sayHello() {
        System.out.println("My Hello World");
    }
}
