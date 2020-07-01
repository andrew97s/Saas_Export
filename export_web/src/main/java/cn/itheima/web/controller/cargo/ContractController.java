package cn.itheima.web.controller.cargo;

import cn.itheima.domain.cargo.Contract;
import cn.itheima.domain.cargo.ContractProductVo;
import cn.itheima.service.cargo.ContractService;
import cn.itheima.utils.DownloadUtil;
import cn.itheima.web.controller.BaseController;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-27 17:37
 **/

@Controller
@RequestMapping("/cargo/contract")
public class ContractController extends BaseController {

    @Reference
    private ContractService contractService;

    @Autowired
    private DownloadUtil downloadUtil;

    //分页查询购销合同列表 且 不同用户查询对应的contractList
    @RequestMapping(value = "/list",name = "展示购销合同列表数据")
    public ModelAndView list(@RequestParam(name = "currentPage",defaultValue = "1",required = false) Integer currentPage , //这个name="page" 不能修改的
                             @RequestParam(name = "pageSize",defaultValue = "6",required = false) Integer pageSize){

        PageInfo<Contract> pageInfo = new PageInfo<>();
        int degree = getUser().getDegree();

        if(degree==4){ //普通人员
            //            create_by=?
           pageInfo = contractService.findByCreatePerson(currentPage, pageSize, getUser().getUserName());
        }else if(degree==3){  //部门经理
            pageInfo= contractService.findByCreateDeptId(currentPage,pageSize,getUser().getDeptId());
        }else if(degree==2){ //总经理 100
            pageInfo= contractService.findByCreateDeptId(currentPage,pageSize,getUser().getDeptId()+"%");
        }

        ModelAndView mv = new ModelAndView("cargo/contract/contract-list");

        mv.addObject("pageInfo",pageInfo);

        return mv;
    }

    @RequestMapping(value="/toAdd",name="进入添加合同的页面")
    public String toAdd() {
        return "/cargo/contract/contract-add";
    }

    @RequestMapping(value="/edit",name="保存合同的方法")
    public String edit(Contract contract) {

        if(StringUtils.isEmpty(contract.getId())) {
            contract.setCreateBy(getUser().getUserName());
            contract.setCreateDept(getUser().getDeptId());
            contract.setId(UUID.randomUUID().toString());
            contract.setState(0); //设置状态  0：草稿 ，1:已确认
            contract.setCompanyId(getCompanyId());
            contract.setCompanyName(getCompanyName());
            contract.setCreateTime(new Date()); //创建时间
            contractService.save(contract);
        }else{
            contractService.update(contract);
        }
        return "redirect:/cargo/contract/list.do";
    }

    @RequestMapping(value="/toUpdate",name="进入修改合同的页面")
    public String toUpdate(String id) {
        Contract contract = contractService.findById(id);
        request.setAttribute("contract",contract);
        return "/cargo/contract/contract-update";
    }

    //提交
    @RequestMapping(value="/submit")
    public String submit(String id) {
        //将合同状态改为1
        Contract contract = new Contract();
        contract.setId(id);
        contract.setState(1);
        contractService.update(contract);
        return "redirect:/cargo/contract/list.do";
    }

    //取消
    @RequestMapping(value="/cancel")
    public String cancel(String id) {
        //将合同状态改为0
        Contract contract = new Contract();
        contract.setId(id);
        contract.setState(0);
        contractService.update(contract);
        return "redirect:/cargo/contract/list.do";
    }

    //删除指定id的contract
    @RequestMapping(value="/delete")
    public String delete(String id) {
        contractService.deleteById(id);
        return "redirect:/cargo/contract/list.do";
    }

    @RequestMapping(value="/print",name="进入到导出出货表页面")
    public String print() {
        return "cargo/print/contract-print";
    }

    @RequestMapping(value = "/printExcel",name = "导出出货表数据")
    public void printExcel(String inputDate) throws Exception{ //inputDate "2015-01"  "2015-12"
//        根据船期查询数据
        List<ContractProductVo> contractProductVoList = contractService.findContractProductVoiByShipTime(inputDate,getCompanyId());

//        导出一个excel
//        创建了一个里面什么都没有的工作薄
        Workbook workbook = new XSSFWorkbook();
//        创建新的工作表sheet
        Sheet sheet = workbook.createSheet("POI测试");
//        设置sheet的列宽
        sheet.setColumnWidth(0,4200); //
        sheet.setColumnWidth(1,26*256); //
        sheet.setColumnWidth(2,16*256); //
        sheet.setColumnWidth(3,26*256); //
        sheet.setColumnWidth(4,16*256); //
        sheet.setColumnWidth(5,16*256); //
        sheet.setColumnWidth(6,16*256); //
        sheet.setColumnWidth(7,16*256); //
        sheet.setColumnWidth(8,16*256); //
        Row bigTitleRow = sheet.createRow(0);
//        创建单元格
        for (int i = 1; i <= 8; i++) {
            bigTitleRow.createCell(i);
        }
//        设置行高
        bigTitleRow.setHeightInPoints(36);
//        合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0,0,1,8));
//        向合并后的单元格中写入一句话：2012年8月份出货表
        Cell cell = bigTitleRow.getCell(1);
        cell.setCellValue(inputDate.replace("-0","年").replace("-","年")  +"月份出货表"); //2015-01"把-0换成“年”  "2015-12"  把-换成“年”
        cell.setCellStyle(bigTitle(workbook));
//        创建小标题行
//        客户
        Row titleRow = sheet.createRow(1);
        String[] titles = new String[]{"客户","合同号","货号","数量","工厂","工厂交期","船期","贸易条款"};
        for (int i = 1; i <= 8; i++) {
//            String title = titles[i];
            cell = titleRow.createCell(i);
            cell.setCellStyle(title(workbook));
            cell.setCellValue(titles[i-1]);
        }
//        内容   contractProductVoList

        int rowIndex=2;
        Row row = null;
        for (ContractProductVo contractProductVo : contractProductVoList) {
            row = sheet.createRow(rowIndex);
//            客户	合同号	货号	数量	工厂	工厂交期	船期	贸易条款
            cell = row.createCell(1);
            cell.setCellValue(contractProductVo.getCustomName());
            cell.setCellStyle(text(workbook));

            cell = row.createCell(2);
            cell.setCellValue(contractProductVo.getContractNo());
            cell.setCellStyle(text(workbook));

            cell = row.createCell(3);
            cell.setCellValue(contractProductVo.getProductNo());
            cell.setCellStyle(text(workbook));

            cell = row.createCell(4);
            cell.setCellValue(contractProductVo.getCNumber());
            cell.setCellStyle(text(workbook));

            cell = row.createCell(5);
            cell.setCellValue(contractProductVo.getFactoryName());
            cell.setCellStyle(text(workbook));
//            工厂交期	船期	贸易条款

            cell = row.createCell(6);
            cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(contractProductVo.getDeliveryDate()));
            cell.setCellStyle(text(workbook));

            cell = row.createCell(7);
            cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(contractProductVo.getShipDate()));
            cell.setCellStyle(text(workbook));

            cell = row.createCell(8);
            cell.setCellValue(contractProductVo.getTradeTerms());
            cell.setCellStyle(text(workbook));
            rowIndex++;
        }

//        把workbook文件导出
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);  //把workbook的内容写入到缓存流中
        downloadUtil.download(byteArrayOutputStream,response,"出货表.xlsx");

    }

    //大标题的样式
    public CellStyle bigTitle(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("楷体");
        font.setFontHeightInPoints((short)16);
        font.setBold(true);//字体加粗
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);                //横向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);     //纵向居中
        return style;
    }
    //小标题的样式
    public CellStyle title(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short)12);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);                //横向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);     //纵向居中
        style.setBorderTop(BorderStyle.THIN);              //上细线
        style.setBorderBottom(BorderStyle.THIN);            //下细线
        style.setBorderLeft(BorderStyle.THIN);             //左细线
        style.setBorderRight(BorderStyle.THIN);             //右细线
        return style;
    }

    //文字样式
    public CellStyle text(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short)10);

        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.LEFT);              //横向居左
        style.setVerticalAlignment(VerticalAlignment.CENTER);     //纵向居中
        style.setBorderTop(BorderStyle.THIN);              //上细线
        style.setBorderBottom(BorderStyle.THIN);            //下细线
        style.setBorderLeft(BorderStyle.THIN);             //左细线
        style.setBorderRight(BorderStyle.THIN);             //右细线

        return style;
    }

}
