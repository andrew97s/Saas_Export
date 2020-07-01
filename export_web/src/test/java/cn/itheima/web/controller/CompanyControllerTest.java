package cn.itheima.web.controller;

import cn.itheima.dao.company.ICompanyDao;
import cn.itheima.service.IDeptService;
import cn.itheima.service.IUserService;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext_service.xml"})
public class CompanyControllerTest {

    @Autowired
    private ICompanyDao dao;

    @Autowired
    private IDeptService deptService;


    @Autowired
    private IUserService userService;


    @Test
    public void findAllCompany() {
        UsernamePasswordToken token = new UsernamePasswordToken("username", "password");
        System.out.println(new String(token.getPassword()));
    }
}