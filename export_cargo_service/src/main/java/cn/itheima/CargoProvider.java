package cn.itheima;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-27 17:33
 **/
public class CargoProvider {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dubbo.xml");
        app.start();

        System.in.read();
    }
}
