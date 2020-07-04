package cn.itheima.web.controller.cargo;

import cn.itheima.dao.cargo.ExportDao;
import cn.itheima.domain.cargo.*;
import cn.itheima.service.cargo.ExportProductService;
import cn.itheima.service.cargo.ExportService;
import cn.itheima.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-07-01 19:36
 * 报运单LifeCycle 0:草稿 1：已上报 2：已报运
 **/

@Controller
@RequestMapping("/cargo/export")
public class ExportController extends BaseController {

    @Reference
    private ExportService exportService;

    @Reference
    private ExportProductService exportProductService;

    @Autowired
    private ExportDao exportDao;

    @RequestMapping("/list")
    public ModelAndView contractList(@RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "6") Integer pageSize){

        ModelAndView mv = new ModelAndView("/cargo/export/export-list");

        ExportExample exportExample = new ExportExample();

        exportExample.setOrderByClause("create_time desc");

        exportExample.createCriteria().andCompanyIdEqualTo(getCompanyId());

        PageInfo<Export> pageInfo = exportService.findByExampleAndPage(currentPage, pageSize, exportExample);

        mv.addObject("pageInfo",pageInfo);

        return mv;
    }



    @RequestMapping(value = "/toExport",name = "进入新增报运单JSP")
    public ModelAndView toExport(String id){

        ModelAndView mv = new ModelAndView("/cargo/export/export-toExport");

        mv.addObject("id",id);

        return mv;
    }

    @RequestMapping(value = "/edit",name = "修改或新增报运单")
    public String edit(Export export){

        if (StringUtils.isEmpty(export.getId())){
            export.setId(UUID.randomUUID().toString());

            export.setCreateBy(getUser().getUserName());
            export.setCreateDept(getUser().getDeptId());
            export.setCreateTime(new Date());
            export.setCompanyId(getCompanyId());
            export.setCompanyName(getCompanyName());

            exportService.save(export);
        }else {
          exportService.update(export);
        }

        return "redirect:/cargo/export/list.do";
    }

    @RequestMapping(value = "/toUpdate",name = "进入修改报运单JSP")
    public ModelAndView toUpdate(String id){

        /*Export export = exportService.findByIds(id);*/
        Export export = exportDao.selectByPrimaryKey(id);

        ModelAndView mv = new ModelAndView("/cargo/export/export-update");

        mv.addObject("export",export);

        ExportProductExample exportProductExample = new ExportProductExample();

        exportProductExample.createCriteria().andExportIdEqualTo(id);

        List<ExportProduct> exportProducts = exportProductService.findByExample(exportProductExample);

        mv.addObject("eps",exportProducts);

        return mv;
    }

    @RequestMapping(value = "/delete",name = "删除指定Id报运单")
    public String delete(String id){

        exportService.deleteById(id);

        return "redirect:/cargo/export/list.do";
    }

    @RequestMapping(value = "/submit",name = "提交报运单")
    public String submit(String id){

        Export export = exportService.findByIds(id);
        export.setState(1);
        exportService.update(export);

        return "redirect:/cargo/export/list.do";
    }

    @RequestMapping(value = "/toView",name = "浏览报运单详情")
    public ModelAndView view(String id){

        Export export = exportDao.selectByPrimaryKey(id);

        ModelAndView mv = new ModelAndView("/cargo/export/export-view");

        mv.addObject("export",export);

        return mv;
    }

    /**
     * @param id
     * @return
     * 将exportVo对象传递至海关WebService服务 并接收相应返回数据进行数据库更改
     */
    @RequestMapping(value = "/exportE",name = "电子报运")
    public String exportE(String id){
        ExportVo exportVo = new ExportVo();

        Export export = exportDao.selectByPrimaryKey(id);

        BeanUtils.copyProperties(export,exportVo);

        exportVo.setExportId(id);

        List<ExportProductVo> exportProductVoList = new ArrayList<>();

        ExportProductExample exportProductExample = new ExportProductExample();

        exportProductExample.createCriteria().andExportIdEqualTo(id);

        List<ExportProduct> exportProductList = exportProductService.findByExample(exportProductExample);

        ExportProductVo exportProductVo = new ExportProductVo();

        for (ExportProduct exportProduct : exportProductList) {
            BeanUtils.copyProperties(exportProduct,exportProductVo);

            exportProductVo.setEid(exportProduct.getId());

            exportProductVoList.add(exportProductVo);
        }

        exportVo.setProducts(exportProductVoList);

        WebClient.create("http://localhost:9090/ws/export/ep").post(exportVo);//海关报运

        ExportResult exportResult = WebClient.create("http://localhost:9090/ws/export/ep/" + id).get(ExportResult.class);//接受海关查询的报运单数据

        exportService.updateE(exportResult);//更新报运单数据

        return "redirect:/cargo/export/list.do";
    }






}
