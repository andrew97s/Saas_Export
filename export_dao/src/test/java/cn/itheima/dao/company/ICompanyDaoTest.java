package cn.itheima.dao.company;

import cn.itheima.dao.cargo.ContractDao;
import cn.itheima.dao.cargo.ContractProductDao;
import cn.itheima.dao.system.IModuleDao;
import cn.itheima.dao.system.IRoleDao;
import cn.itheima.dao.system.ISysLogDao;
import cn.itheima.dao.system.IUserDao;
import cn.itheima.domain.cargo.Contract;
import cn.itheima.domain.cargo.ContractProduct;
import cn.itheima.domain.cargo.ExtCproduct;
import cn.itheima.domain.system.SysLog;
import cn.itheima.utils.FileUpLoadUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-dao.xml")

public class ICompanyDaoTest {

    @Autowired
    private ICompanyDao dao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IModuleDao moduleDao;

    @Autowired
    private ISysLogDao sysLogDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ContractProductDao contractProductDao;

    @Autowired
    private FileUpLoadUtils fileUpLoadUtils;


    @Test
    public void findAll() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("testSheet1");
        XSSFSheet sheet1 = workbook.createSheet("testSheet2");


        sheet.setColumnWidth(0,17*256);
        sheet.setColumnWidth(1,2*17*256);
        sheet.setColumnWidth(2,3*17*256);
        XSSFRow row1 = sheet.createRow(0);
        XSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("row1cell1");

        XSSFCell cell3 = row1.createCell(1);
        cell3.setCellValue("row1Cell2");

        XSSFRow row2 = sheet.createRow(1);
        XSSFCell cell2 = row2.createCell(0);
        cell2.setCellValue("row2cell1");

        sheet1.setColumnWidth(0,17*256);
        sheet1.setColumnWidth(1,2*17*256);
        sheet1.setColumnWidth(2,3*17*256);
        XSSFRow rows1 = sheet1.createRow(0);
        XSSFCell cells1 = rows1.createCell(0);
        cells1.setCellValue("row1cell1");

        XSSFCell cells3 = rows1.createCell(1);
        cells3.setCellValue("row1Cell2");

        XSSFRow rows2 = sheet1.createRow(1);
        XSSFCell cells2 = rows2.createCell(0);
        cells2.setCellValue("row2cell1");

        workbook.write(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test.xlsx"));
    }

    @Test
    public void test() throws IOException {

        Workbook workbook = new XSSFWorkbook();

//        创建样式、
        CellStyle cellStyle = workbook.createCellStyle();
//        居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);  //水平对齐方式
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);  //垂直对齐方式
//       字体  宋体 16号 加粗
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        cellStyle.setFont(font);


//        创建样式、
        CellStyle cellStyle1 = workbook.createCellStyle();
//        居中对齐
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);  //水平对齐方式
        cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);  //垂直对齐方式

        cellStyle1.setBorderBottom(BorderStyle.THIN); //下边框 细线
        cellStyle1.setBorderLeft(BorderStyle.THIN);
        cellStyle1.setBorderRight(BorderStyle.THIN);
        cellStyle1.setBorderTop(BorderStyle.THIN);

//       字体  黑体 12号
        Font font1 = workbook.createFont();
        font1.setFontName("黑体");
        font1.setFontHeightInPoints((short) 12);
        font1.setBold(false);
        cellStyle1.setFont(font1);

//        创建新的工作表sheet
        Sheet sheet = workbook.createSheet("POI测试");
//        设置sheet的列宽
        sheet.setColumnWidth(0,4200); //1代表一个字母宽度的256分之一
        sheet.setColumnWidth(1,26*256); //1代表一个字母宽度的256分之一
        sheet.setColumnWidth(2,16*256); //1代表一个字母宽度的256分之一
        sheet.setColumnWidth(3,26*256); //1代表一个字母宽度的256分之一
        sheet.setColumnWidth(4,16*256); //1代表一个字母宽度的256分之一
        sheet.setColumnWidth(5,16*256); //1代表一个字母宽度的256分之一
        sheet.setColumnWidth(6,16*256); //1代表一个字母宽度的256分之一
        sheet.setColumnWidth(7,16*256); //1代表一个字母宽度的256分之一
        sheet.setColumnWidth(8,16*256); //1代表一个字母宽度的256分之一
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
        cell.setCellValue("2012年8月份出货表");
        cell.setCellStyle(cellStyle);
//        创建小标题行
//        客户
        Row titleRow = sheet.createRow(1);
        Cell cell1 = titleRow.createCell(1);
        cell1.setCellValue("客户");
        cell1.setCellStyle(cellStyle1);

//        把excel输出到我的磁盘上
        workbook.write(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test.xlsx"));
    }


}