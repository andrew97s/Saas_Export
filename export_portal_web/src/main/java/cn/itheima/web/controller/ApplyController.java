package cn.itheima.web.controller;

import cn.itheima.domain.system.Company;
import cn.itheima.service.ICompanyService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-27 08:51
 **/

@Controller
public class ApplyController {

    @Reference
    private ICompanyService companyService;

    @RequestMapping("/apply")
    @ResponseBody
    public String apply(Company company){
        try {
            company.setId(UUID.randomUUID().toString());  //id 赋值成一个随机id
            company.setState(0); //未审核
            companyService.saveCompany(company);
            return "1"; //表示成功
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(Company company){
        List<Company> list = companyService.findAllCompany();
        for (Company company1 : list) {
            System.out.println(company1);
        }
        return list.get(1).getId();
    }
}
