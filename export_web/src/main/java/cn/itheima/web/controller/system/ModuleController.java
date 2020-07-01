package cn.itheima.web.controller.system;

import cn.itheima.domain.system.Module;
import cn.itheima.service.IModuleService;
import com.github.pagehelper.PageInfo;
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
 * <p>controller of module</p>
 *
 * @author : Andrew
 * @date : 2020-06-23 14:40
 **/

@Controller
@RequestMapping("/system/module")
public class ModuleController {

    @Autowired
    private IModuleService moduleService;

    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize
    ){

        ModelAndView mv = new ModelAndView();

        mv.setViewName("/system/module/module-list");

        PageInfo<Module> pageInfo = moduleService.findModuleByPage(currentPage, pageSize);

        mv.addObject("pageInfo",pageInfo);

        return mv;
    }

    @RequestMapping("/toAdd")
    public ModelAndView toAdd(){

        ModelAndView mv = new ModelAndView();

        mv.setViewName("/system/module/module-add");

        List<Module> menus = moduleService.findAllModule();

        mv.addObject("menus",menus);

        return mv;
    }

    @RequestMapping("/edit")
    public String  edit(Module module){

        if (StringUtils.isEmpty(module.getId())){

            module.setId(UUID.randomUUID().toString());

            moduleService.addModule(module);

        }else {

            moduleService.updateModule(module);
        }

        return "redirect:/system/module/list.do";
    }

    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(String id){

        ModelAndView mv = new ModelAndView();

        mv.setViewName("/system/module/module-update");

        Module module = moduleService.findModuleById(id);

        List<Module> moduleList = moduleService.findAllModule();

        mv.addObject("menus",moduleList);

        mv.addObject("module",module);

        return mv;
    }

    @RequestMapping("delete")
    public String delete(String id){

        moduleService.deleteModule(id);

        return "redirect:/system/module/list.do";
    }


}
