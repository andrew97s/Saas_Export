package cn.itheima.web.controller.stat;

import cn.itheima.service.stat.StatService;
import cn.itheima.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-07-03 21:26
 **/
@Controller
@RequestMapping("/stat")
public class StatController extends BaseController {

    @Reference
    private StatService statService;

    @RequestMapping("/toCharts")
    public String toCharts(String chartType){
        switch (chartType){
            case "factoryCharts":
                return "/stat/stat-factory";
            case "sellCharts":
                return "/stat/stat-sell";
            case "onlineCharts":
                return "/stat/stat-online";
            default:
                return null;
        }

    }

    @RequestMapping(value = "/factoryCharts",name = "每个厂家的销售金额统计")
    @ResponseBody
    public List<Map> factoryCharts(){
        //        [{name:AA,value:1},{name:BB,value:12}]
        //        sell factory online
        List<Map> result = statService.findByProductNum(getCompanyId());

        return result;
    }

    @RequestMapping(value = "/sellCharts",name = "每个厂家的销售金额统计")
    @ResponseBody
    public List<Map> sellCharts(){
        //        [{name:AA,value:1},{name:BB,value:12}]
        //        sell factory online
        return statService.findByCompanyAmount(getCompanyId());

    }

    @RequestMapping(value = "/onlineCharts",name = "每个厂家的销售金额统计")
    @ResponseBody
    public List<Map> onlineChats(){
        //        [{name:AA,value:1},{name:BB,value:12}]
        //        sell factory online
        return statService.findBySysLoad(getCompanyId());
    }


}
