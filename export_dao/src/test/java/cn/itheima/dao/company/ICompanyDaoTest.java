package cn.itheima.dao.company;

import cn.itheima.domain.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_dao.xml")

public class ICompanyDaoTest {

    @Autowired
    private ICompanyDao dao;

    @Test
    public void findAll() {
        Company company = dao.findById("5ddb850f-99f4-4b0e-8406-4b01f30faded");
        System.out.println(company);
    }

    @Test
    public void test(){

    }
}