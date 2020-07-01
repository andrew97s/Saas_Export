package cn.itheima.web.controller.cargo;

import cn.itheima.dao.cargo.ContractProductDao;
import cn.itheima.domain.cargo.ContractProduct;
import cn.itheima.domain.cargo.Factory;
import cn.itheima.service.cargo.ContractProductService;
import cn.itheima.service.cargo.FactoryService;
import cn.itheima.utils.FileUpLoadUtils;
import cn.itheima.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-28 08:55
 **/

@Controller
@RequestMapping("/cargo/contractProduct")
public class ContractProductController extends BaseController {

    @Reference
    private ContractProductService contractProductService;

    @Reference
    private FactoryService factoryService;

    @Autowired
    private ContractProductDao contractProductDao;

    @Autowired
    private FileUpLoadUtils fileUpLoadUtils;

    @RequestMapping(value = "/list",name = "展示购销合同下货物列表数据")
    public String list(String contractId, @RequestParam(name = "currentPage",defaultValue = "1") Integer currentPage , //这个name="page" 不能修改的
                       @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize){
//        跳转到新增货物并且显示货物列表的页面
//        需要向request域中放两个数据：1、用来生成货物的工厂  2、这个合同下的所有货物数据
//         查询用来生成货物的工厂数据 select * from co_factory where ctype='货物'

        List<Factory> factoryList = factoryService.findByCtype("货物");
       /* FactoryExample factoryExample = new FactoryExample();
        factoryExample.createCriteria().andCtypeEqualTo("货物");
        List<Factory> factoryList = factoryService.findAll(factoryExample);*/
        request.setAttribute("factoryList",factoryList);
//        查询此合同下的所有货物数据  select * from 货物表 where 合同id=？
      /*  ContractProductExample example = new ContractProductExample();
        example.createCriteria().andContractIdEqualTo(contractId);*/
        PageInfo pageInfo = contractProductService.findByPage(currentPage,pageSize,contractId);
        request.setAttribute("pageInfo",pageInfo);

        // 为了保存货物时关联合同
        request.setAttribute("contractId",contractId);

        return "cargo/product/product-list";
    }

    @RequestMapping(value="/edit",name="保存合同下货物的方法")
    public String edit(ContractProduct contractProduct,MultipartFile productPhoto) {

        String upload=null;

        try {
            if (productPhoto!=null){
                upload=fileUpLoadUtils.upload(productPhoto);
            }
        } catch (Exception e) {
            upload=null;
        }

        contractProduct.setProductImage(upload);

        contractProduct.setCompanyId(getCompanyId());
        contractProduct.setCompanyName(getCompanyName());

        if(StringUtils.isEmpty(contractProduct.getId())) {
            //		1.设置货物id
            contractProduct.setId(UUID.randomUUID().toString());
            contractProductService.save(contractProduct);
        }else{
            contractProductService.update(contractProduct);
        }
        //页面跳转
        return "redirect:/cargo/contractProduct/list.do?contractId="+contractProduct.getContractId();
    }

    @RequestMapping(value="/toUpdate",name="进入修改合同下的货物页面")
    public String toUpdate(String id) {

        /*ContractProduct contractProduct = contractProductService.findById(id);*/

        ContractProduct contractProduct = contractProductDao.selectByPrimaryKey(id);
        request.setAttribute("contractProduct",contractProduct);

        //查询所有的厂家列表
        /*FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria1 = factoryExample.createCriteria();
        criteria1.andCtypeEqualTo("货物");
        List factoryList = factoryService.findAll(factoryExample);*/

        List<Factory> factoryList = factoryService.findByCtype("货物");

        request.setAttribute("factoryList",factoryList);
        return "/cargo/product/product-update";
    }

    @RequestMapping(value="/delete",name="删除货物方法")
    public String delete(String id,String contractId) {
        contractProductService.deleteById(id);
        return "redirect:/cargo/contractProduct/list.do?contractId="+contractId;
    }

    @RequestMapping(value = "/toImport",name = "跳转到上传货物页面")
    public String toImport(String contractId){
        request.setAttribute("contractId",contractId);
        return "cargo/product/product-import"; //跳转到上传货物页面
    }

    /**
     * @param contractId
     * @param file
     * @return
     * @throws Exception
     *
     * 利用POI读取excel文档（特定表结构） 并将每行表数据封装为 响应的对象
     */
    @RequestMapping(value="/import" ,name="上传购销合同货物方法")
    public String importXls(String contractId,MultipartFile file) throws Exception {
        //        1、创建一个的工作薄
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
//        2、获取一个工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        int lastRowIndex = sheet.getLastRowNum(); //读取sheet的最后一行索引值
//        生产厂家	货号	数量	包装单位(PCS/SETS)	装率	箱数	单价	货物描述	要求
        List<ContractProduct> list = new ArrayList<>(); //准备接收所有的需要保存的货物对象 一次性提交事务
        for (int i = 1; i <= lastRowIndex; i++) {
            XSSFRow row = sheet.getRow(i);  //获取行
            ContractProduct product = new ContractProduct(); //货物
            String factoryName = row.getCell(1).getStringCellValue();//生产厂家
            product.setFactoryName(factoryName);
            String productNo = row.getCell(2).getStringCellValue();//货号
            product.setProductNo(productNo);
            Integer cnumber = ((Double) row.getCell(3).getNumericCellValue()).intValue();//数量
            product.setCnumber(Long.valueOf(cnumber));
            String packingUnit = row.getCell(4).getStringCellValue();//包装单位(PCS/SETS)
            product.setPackingUnit(packingUnit);
            String loadingRate = row.getCell(5).getNumericCellValue() + ""; //装率
            product.setLoadingRate(loadingRate);
            Integer boxNum = ((Double) row.getCell(6).getNumericCellValue()).intValue();//数量
            product.setBoxNum(Long.valueOf(boxNum));
            Double price = row.getCell(7).getNumericCellValue(); //单价
            product.setPrice(price);
            String productDesc = row.getCell(8).getStringCellValue(); // 货物描述
            product.setProductDesc(productDesc);
            String productRequest = row.getCell(9).getStringCellValue(); // 要求
            product.setProductRequest(productRequest);
            product.setContractId(contractId); // 设置合同id

            //        新增时需要赋id
            product.setCompanyId(getCompanyId());
            product.setCompanyName(getCompanyName());
            product.setId(UUID.randomUUID().toString());  //id 赋值成一个随机id

            product.setCreateDept(getUser().getDeptId());
            product.setCreateBy(getUser().getId());
            product.setCreateTime(new Date());

            list.add(product);
        }

        contractProductService.saveList(list); //一次性保存
        return "redirect:/cargo/contractProduct/list.do?contractId=" + contractId;  //重定向到列表数据
    }
}
