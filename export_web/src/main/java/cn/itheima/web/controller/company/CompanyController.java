package cn.itheima.web.controller.company;

import cn.itheima.domain.system.Company;
import cn.itheima.service.ICompanyService;
import cn.itheima.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

/**
 * <h3>export_parent</h3>
 * <p>the controller of company</p>
 *
 * @author : Andrew
 * @date : 2020-06-19 20:51
 **/
@Controller
@RequestMapping("/company")
@RequiresAuthentication
public class CompanyController extends BaseController {

    @Autowired
    private ICompanyService companyService;

    @RequestMapping("/list")
    public ModelAndView findAllCompany(
            @RequestParam(value = "currentPage",required = false,defaultValue ="1")Integer currentpage,
            @RequestParam(value = "pageSize",required = false,defaultValue = "5")Integer pageSize)
    {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("company/company-list");

        PageInfo<Company> pageInfo = companyService.findByPage(currentpage,pageSize);

        mv.addObject("pageInfo",pageInfo);

        return mv;
    }

    @RequestMapping("/toAdd")
    public String  addCompany(){

        return "company/company-add";
    }

    @RequestMapping("/edit")
    public String editCompany(Company company){

        if (!StringUtils.isEmpty(company.getId())){
            companyService.updateCompany(company);

        }else {
            company.setId(UUID.randomUUID().toString());
            companyService.saveCompany(company);
        }

        return "redirect:/company/list.do";
    }

    @RequestMapping("/toUpdate")
    public ModelAndView updateCompany(String id){

        ModelAndView mv = new ModelAndView();

        mv.setViewName("company/company-update");

        Company company = companyService.findCompanyById(id);


        mv.addObject("company",company);

        return mv;
    }

    @RequestMapping("/delete")
    public String  deleteCompany(String id){

       companyService.deleteCompanyById(id);

       return "redirect:/company/list.do";
    }


}
