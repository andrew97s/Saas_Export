package cn.itheima.service.stat.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-07-03 22:08
 **/
public class Statprovider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext-dubbo-stat.xml");

        app.start();

        System.in.read();
    }
}
