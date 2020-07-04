package cn.itheima.web.controller.export;

import cn.itheima.service.cargo.ExportService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-07-01 16:04
 **/
public class ExportController {

    @Autowired
    private ExportService exportService;
/*
    @RequestMapping(value = "/exportE",name = "海关电子报运方法")
    public String exportE(String id){ //报运单的ID
        //WebService远程调用
        ExportVo exportVo = new ExportVo();
        Module.Export export = exportService.findById(id);
        BeanUtils.copyProperties(export,exportVo);
        //        ExportVo需要手动设置exportId来自于Export的id，List<ExportProductVo>
        exportVo.setExportId(id);

        List<ExportProductVo> exportProductVoList = new ArrayList<>(); //接收需要放入到exportVo货物Vo的集合
        //        查询此报运单的下的货物，把它们都转成ExportProductVo
        List<ExportProduct> exportProductList = exportProductService.findByExportId(id);
        ExportProductVo exportProductVo = null;
        for (ExportProduct exportProduct : exportProductList) {
            exportProductVo = new ExportProductVo();
            BeanUtils.copyProperties(exportProduct,exportProductVo);
            //            需要手动个Vo设置的是：eid 、exportProductId
            exportProductVo.setEid(exportVo.getId());
            exportProductVo.setExportProductId(exportProduct.getId());
            exportProductVoList.add(exportProductVo);
        }
        exportVo.setProducts(exportProductVoList);
        //        向海关传输数据
        WebClient.create("http://localhost:9090/ws/export/ep").post(exportVo);
        //        向海关获取结果数据

        ExportResult exportResult = WebClient.create("http://localhost:9090/ws/export/ep/" + id).get(ExportResult.class);
        exportService.updateE(exportResult); //把海关返回的数据更新到我们的表中
        return "redirect:/cargo/export/list.do";
    }*/
}
