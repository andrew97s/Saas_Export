package cn.itheima.web.controller.cargo;

import cn.itheima.domain.cargo.ExtCproduct;
import cn.itheima.domain.cargo.Factory;
import cn.itheima.service.cargo.ExtCproductService;
import cn.itheima.service.cargo.FactoryService;
import cn.itheima.utils.FileUpLoadUtils;
import cn.itheima.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-28 20:47
 **/

@Controller
@RequestMapping("/cargo/extCproduct")
public class ExtCproductController extends BaseController {

    @Reference
    private ExtCproductService extCproductService;

    @Reference
    private FactoryService factoryService;

    @Autowired
    private FileUpLoadUtils fileUpLoadUtil;

    @RequestMapping(value = "/list",name = "展示购销合同货物下附件列表数据")
    public String list(String contractId,String contractProductId,@RequestParam(name = "currentPage",defaultValue = "1") Integer currentPage ,
                       @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize){

        List<Factory> factoryList = factoryService.findByCtype("附件");

        PageInfo<ExtCproduct> pageInfo = extCproductService.findByPage(currentPage, pageSize, contractProductId);

        request.setAttribute("pageInfo",pageInfo);

        request.setAttribute("contractProductId",contractProductId);

        request.setAttribute("factoryList",factoryList);

        request.setAttribute("contractId",contractId);

        return "cargo/extc/extc-list";
    }

    @RequestMapping(value = "/edit",name = "保存购销合同货物下附件数据")
    public String edit(ExtCproduct extCproduct, MultipartFile productPhoto){

        String upload = null;
        try {
            if(productPhoto!=null){
                upload = fileUpLoadUtil.upload(productPhoto);
            }
        } catch (Exception e) {
            upload = null;
        }

        extCproduct.setProductImage(upload);

        if(StringUtils.isEmpty(extCproduct.getId())){
            extCproduct.setId(UUID.randomUUID().toString());
            extCproduct.setCompanyId(getCompanyId());
            extCproduct.setCompanyName(getCompanyName());
            extCproductService.save(extCproduct);
        }else{
            extCproductService.update(extCproduct);
        }

        return "redirect:/cargo/extCproduct/list.do?&contractId="+extCproduct.getContractId()+"&contractProductId="+extCproduct.getContractProductId();
    }

    @RequestMapping(value = "/toUpdate",name = "跳转到购销合同货物下附件编辑页面")
    public String toUpdate(String id){

        ExtCproduct extCproduct = extCproductService.findById(id);
        request.setAttribute("extCproduct",extCproduct);

        List<Factory> factoryList = factoryService.findByCtype("附件");
        request.setAttribute("factoryList",factoryList);

        request.setAttribute("contractProductId",extCproduct.getContractProductId());

        request.setAttribute("contractId",extCproduct.getContractId());

        return "cargo/extc/extc-update";
    }

    @RequestMapping(value = "/delete",name = "删除购销合同货物下附件")
    public String delete(String id,String contractProductId,String contractId){

        extCproductService.deleteById(id);

        return "redirect:/cargo/extCproduct/list.do?&contractProductId="+contractProductId+"&contractId="+contractId;
    }




}
