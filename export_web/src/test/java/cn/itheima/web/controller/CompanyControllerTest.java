package cn.itheima.web.controller;

import cn.itheima.domain.Company;
import cn.itheima.service.ICompanyService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext_service.xml"})
public class CompanyControllerTest {

    @Autowired
    private ICompanyService companyService;

    @Test
    public void findAllCompany() {

        PageInfo<Company> page = companyService.findByPage(1, 5);
        List<Company> list = page.getList();
        for (Company company : list) {
            System.out.println(company);
        }
        System.out.println(page);
    }
}