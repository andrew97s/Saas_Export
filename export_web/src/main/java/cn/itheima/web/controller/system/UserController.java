package cn.itheima.web.controller.system;

import cn.itheima.domain.system.Dept;
import cn.itheima.domain.system.Role;
import cn.itheima.domain.system.User;
import cn.itheima.service.IDeptService;
import cn.itheima.service.IRoleService;
import cn.itheima.service.IUserService;
import cn.itheima.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p>controller of user</p>
 *
 * @author : Andrew
 * @date : 2020-06-23 09:27
 **/

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize
    ) {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/system/user/user-list");

        PageInfo<User> pageInfo = userService.findBYPage(currentPage, pageSize);

        mv.addObject("pageInfo", pageInfo);

        return mv;
    }

    @RequestMapping("/toAdd")
    public String toAdd(){

        return "/system/user/user-add";
    }

    @RequestMapping("/delete")
    public String delete(String id){

        userService.deleteUserById(id);

        return "redirect:/system/user/list.do";
    }

    @RequestMapping("/roleList")
    public ModelAndView roleList(String id){

        ModelAndView mv = new ModelAndView();

        mv.setViewName("/system/user/user-role");

        List<Role> roles_enable = userService.findRolesByUserId(id);

        /*遍历用户已拥有的roleId 并传递至前端页面*/
        StringBuilder rolesStr= new StringBuilder();

        for (Role role : roles_enable) {
           rolesStr.append(role.getId()).append(";");
        }

        User user = userService.findUserById(id);

        List<Role> roles_all = roleService.findAllRoles(getCompanyId());

        mv.addObject("userRoleStr",rolesStr.toString());

        mv.addObject("roleList",roles_all);

        mv.addObject("user",user);

        return mv;
    }


    /**
     * @param userId
     * @param roleIds
     * @return
     * 通过将用户的全部角色删除在添加用户当前全部角色实现用户角色更新操作
     */
    @RequestMapping("/changeRole")
    public String changRole(String userId,String[] roleIds){

        userService.addRolesForUser(userId,roleIds);

        return "redirect:/system/user/list.do";
    }

    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(String id){

        ModelAndView mv = new ModelAndView();

        mv.setViewName("/system/user/user-update");

        User user = userService.findUserById(id);

        List<Dept> deptList = deptService.findAllDept(getCompanyId());

        mv.addObject("user",user);
        mv.addObject("deptList",deptList);

        return mv;
    }



}
