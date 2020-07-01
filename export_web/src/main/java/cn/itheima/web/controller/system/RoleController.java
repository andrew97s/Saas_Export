package cn.itheima.web.controller.system;

import cn.itheima.domain.system.Module;
import cn.itheima.domain.system.Role;
import cn.itheima.service.IModuleService;
import cn.itheima.service.IRoleService;
import cn.itheima.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * <h3>export_parent</h3>
 * <p>controller of role</p>
 *
 * @author : Andrew
 * @date : 2020-06-23 16:25
 **/

@Service
@RequestMapping("/system/role")
public class RoleController  extends BaseController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IModuleService moduleService;

    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize

    ){
        PageInfo<Role> pageInfo = roleService.findByPage(currentPage, pageSize, getCompanyId());

        ModelAndView mv = new ModelAndView("/system/role/role-list");

        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    @RequestMapping("/toAdd")
    public String  toAdd(){

        return "/system/role/role-add";
    }

    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(String id){

        ModelAndView mv = new ModelAndView("/system/role/role-update");

        Role role = roleService.findRoleById(id, getCompanyId());

        mv.addObject("role",role);

        return mv;
    }

    @RequestMapping("/edit")
    public String edit(Role role){

        if (StringUtils.isEmpty(role.getId())){

            role.setId(UUID.randomUUID().toString());

            roleService.addRole(role);
        }else {

            roleService.updateRole(role);
        }

        return "redirect:/system/role/list.do";
    }

    @RequestMapping("/delete")
    public String delete(String id){

        roleService.deleteRoleById(id);

        return "redirect:/system/role/list.do";
    }

    @RequestMapping("roleModule")
    public ModelAndView moduleList(String roleId){

        ModelAndView mv = new ModelAndView("/system/role/role-module");

        Role role = roleService.findRoleById(roleId, getCompanyId());

        mv.addObject("role",role);

        return mv;
    }

    @RequestMapping("/getZTreeNodes")
    @ResponseBody
    public List<Map> getZTreeNode(String roleId){

        List<Module> module_enable = roleService.findModulesByRoleId(roleId);

        List<Module> moduleAll = moduleService.findAllModule();

        List<Map> moduleList = new ArrayList<>();

        for (Module module : moduleAll) {

            HashMap<String, Object> map = new HashMap<>();

            map.put("id",module.getId());
            map.put("pId",module.getParentId());
            map.put("name",module.getName());

            for (Module m : module_enable) {

                if (module.getId().equals(m.getId())){
                    map.put("checked",true);
                }
            }

            moduleList.add(map);
        }

        return moduleList;
    }

    @RequestMapping("/updateRoleModule")
    public String updateRoleModule(String roleId,String moduleIds){

        String[] moduleId = moduleIds.split(",");



        roleService.updateRoleModule(roleId,moduleId);

        return "redirect:/system/role/list.do";
    }

}
