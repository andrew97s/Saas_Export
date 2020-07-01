package cn.itheima.web.controller.system;

import cn.itheima.domain.system.Dept;
import cn.itheima.service.ICompanyService;
import cn.itheima.service.IDeptService;
import cn.itheima.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

/**
 * <h3>export_parent</h3>
 * <p>the controller of department</p>
 *
 * @author : Andrew
 * @date : 2020-06-22 08:19
 **/
@Controller
@RequestMapping("/system/dept")

public class DeptController extends BaseController {

    @Autowired
    private IDeptService deptService;

    @Autowired
    private ICompanyService companyService;

    @RequiresPermissions({"部门管理"})
    @RequestMapping(value = "/list",name = "分页查询部门信息")
    public ModelAndView list(@RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
                             @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize)
    {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("system/dept/dept-list");

        PageInfo<Dept> pageInfo = deptService.findByPage(currentPage, pageSize,getCompanyId());

        mv.addObject("pageInfo",pageInfo);

        return mv;
    }

    @RequestMapping("/toUpdate")
    public ModelAndView findById(String id){
        Dept dept = deptService.findDeptById(id);

        ModelAndView mv = new ModelAndView();

        mv.setViewName("system/dept/dept-update");

        mv.addObject("dept",dept);

        List<Dept> deptList = deptService.findAllDept(getCompanyId());


        mv.addObject("deptList",deptList);

        return mv;
    }

    /**
     * @return 转发到新增dept页面
     */
    @RequestMapping("/toAdd")
    public ModelAndView  add(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("system/dept/dept-add");

        List<Dept> deptList = deptService.findAllDept(getCompanyId());

        mv.addObject("deptList",deptList);

        return mv;
    }

    /**
     * @param dept 前端传递的dept对象
     * @return 转发到list处理
     *
     * 当前端传递的dept对象id不为空代表更新操作 否则代表新建
     */
    @RequestMapping("/edit")
    public String  edit(Dept dept){


        dept.setCompanyId(getCompanyId());
        dept.setCompanyName(getCompanyName());

        if (StringUtils.isEmpty(dept.getId())){

            dept.setId(UUID.randomUUID().toString());
            deptService.addDept(dept);

        }else {
            deptService.updateDept(dept);
        }

        return "forward:/system/dept/list.do";
    }

    @RequestMapping("/delete")
    public String delete(String id){

        deptService.deleteDeptById(id);

        return "forward:/system/dept/list.do";
    }


}
