package cn.itheima.web.controller.system;


import cn.itheima.domain.system.Module;
import cn.itheima.domain.system.User;
import cn.itheima.service.IModuleService;
import cn.itheima.service.IUserService;
import cn.itheima.web.controller.BaseController;

import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IModuleService moduleService;

   @RequestMapping("/login")
    public ModelAndView login(String email, String password){

       ModelAndView mv = new ModelAndView();

       if (!StringUtils.isEmpty(email)&&!StringUtils.isEmpty(password)){

               UsernamePasswordToken token = new UsernamePasswordToken(email, password);

               try {
                   SecurityUtils.getSubject().login(token);

               }catch (UnknownAccountException e){
                   e.printStackTrace();

                   mv.addObject("errorMsg","邮箱或密码错误");

                   mv.setViewName("forward:login.jsp");

                   return mv;
               }

               User user = (User) SecurityUtils.getSubject().getPrincipal();

               session.setAttribute("loginUser",user);

               List<Module> moduleList = moduleService.findModulesByUser(user);

               session.setAttribute("moduleList",moduleList);

               mv.setViewName("home/main");


       }else {
           mv.addObject("errorMsg","用户名或密码不能为空！");
           mv.setViewName("forward:/login.jsp");
       }

       return mv;
    }

    @RequestMapping("/home")
    public String  home(){


        return "home/home";
    }

    @RequestMapping("/logout")
    public String logout(){

        SecurityUtils.getSubject().logout();

        return "forward:/login.jsp";
    }


}
