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
        List<Company> all = dao.findAll();
        for (Company company : all) {
            System.out.println(company);
        }
    }

    @Test
    public void test(){

    }
}