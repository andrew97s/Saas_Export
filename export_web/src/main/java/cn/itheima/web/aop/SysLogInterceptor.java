package cn.itheima.web.aop;

import cn.itheima.domain.system.SysLog;
import cn.itheima.domain.system.User;
import cn.itheima.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.UUID;

/**
 * <h3>export_parent</h3>
 * <p>interceptor of system log</p>
 *
 * @author : Andrew
 * @date : 2020-06-25 10:11
 *
 * 通过aop对controller所有操作进行记录
 **/
/*@Component("sysLogger")*/
@Aspect
public class SysLogInterceptor {


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    private ISysLogService sysLogService;

    private SysLog sysLog=new SysLog();


    @Around("execution(* cn.itheima.web.controller.*.*.*(..))")
    public void init(JoinPoint pjp){
        String id = UUID.randomUUID().toString();

        String ip = request.getRemoteAddr();

        MethodSignature methodSignature =(MethodSignature) pjp.getSignature();

        Method method = methodSignature.getMethod();

        String method_mapping = method.getAnnotation(RequestMapping.class).value()[0];

        if (pjp.getTarget().getClass().getAnnotation(RequestMapping.class)!=null){
            String class_mapping = pjp.getTarget().getClass().getAnnotation(RequestMapping.class).value()[0];

            sysLog.setMethod(class_mapping);
        }

        if (session.getAttribute("loginUser")!=null){
            User user = (User) session.getAttribute("loginUser");

            String username = user.getUserName();

            String companyId = user.getCompanyId();

            String companyName = user.getCompanyName();

            sysLog.setCompanyId(companyId);
            sysLog.setCompanyName(companyName);
            sysLog.setUsername(username);
        }

        sysLog.setId(id);
        sysLog.setIp(ip);

        sysLog.setAction(method_mapping);
        sysLog.setTime(new Date(System.currentTimeMillis()));
        sysLogService.add(sysLog);

    }



}
