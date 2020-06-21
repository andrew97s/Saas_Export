package cn.itheima.web.controller;

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
}
