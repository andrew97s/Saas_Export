package cn.itheima.web.controller;

import cn.itheima.domain.Company;
import cn.itheima.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p>the controller of company</p>
 *
 * @author : Andrew
 * @date : 2020-06-19 20:51
 **/
@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @RequestMapping("/findAll")
    public ModelAndView findAllCompany(){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("company/company-list");

        List<Company> companies = companyService.findAllCompany();

        mv.addObject("list",companies);

        return mv;
    }

}
