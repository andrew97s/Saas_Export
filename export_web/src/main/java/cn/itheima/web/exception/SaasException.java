package cn.itheima.web.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h3>export_parent</h3>
 * <p>define a Saas Exception</p>
 *
 * @author : Andrew
 * @date : 2020-06-20 11:36
 **/

@Component
public class SaasException implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        ModelAndView mv = new ModelAndView();

        mv.addObject("errorMsg",e.getMessage());

        mv.setViewName("error");

        return mv;
    }
}
