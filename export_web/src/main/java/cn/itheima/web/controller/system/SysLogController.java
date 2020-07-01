package cn.itheima.web.controller.system;

import cn.itheima.domain.system.SysLog;
import cn.itheima.service.ISysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-25 09:50
 **/
@Controller
@RequestMapping("/system/log")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize",required = false,defaultValue = "8") Integer pageSize
    ){

        PageInfo<SysLog> pageInfo = sysLogService.findSysLogByPage(currentPage, pageSize);

        ModelAndView mv = new ModelAndView("/system/log/log-list");

        mv.addObject("pageInfo",pageInfo);

        return mv;
    }
}
