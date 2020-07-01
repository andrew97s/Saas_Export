package cn.itheima.web.controller;

import cn.itheima.domain.system.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <h3>export_parent</h3>
 * <p>this servlet contain request&response&session </p>
 *
 * @author : Andrew
 * @date : 2020-06-20 17:00
 **/
public class BaseController {

    @Autowired()
    protected HttpServletRequest request;

    @Autowired(required = false)
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    /**
     * @return
     *
     * 该方法会被作为使用者登录信息存放在session
     */
    protected String getCompanyId(){
        if (getUser()!=null){
            return getUser().getCompanyId();
        }
        return null;
    }

    protected String getCompanyName(){
        if (getUser()!=null){
            return getUser().getCompanyName();
        }
        return null;
    }

    protected User getUser(){

        if (session.getAttribute("loginUser")!=null){
            return (User)session.getAttribute("loginUser");
        }

        return null;
    }
}
