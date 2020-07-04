package cn.itheima.web.controller;

import cn.itheima.dao.company.ICompanyDao;
import cn.itheima.dao.stat.StatDao;
import cn.itheima.service.IDeptService;
import cn.itheima.service.IUserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext_service.xml"})
public class CompanyControllerTest {

    @Autowired
    private ICompanyDao dao;

    @Autowired
    private IDeptService deptService;


    @Autowired
    private IUserService userService;

    @Autowired
    private StatDao statDao;


    @Test
    public void findAllCompany() {
        List<Map> list = statDao.findByCompanyAmount("1");

        Object o = JSONArray.toJSON(list);
        System.out.println(o);
    }
}