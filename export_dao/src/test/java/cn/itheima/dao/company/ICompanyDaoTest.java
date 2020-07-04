package cn.itheima.dao.company;

import cn.itheima.dao.cargo.ContractDao;
import cn.itheima.dao.cargo.ContractProductDao;
import cn.itheima.dao.cargo.ExportDao;
import cn.itheima.dao.cargo.ExportProductDao;
import cn.itheima.dao.stat.StatDao;
import cn.itheima.dao.system.IModuleDao;
import cn.itheima.dao.system.IRoleDao;
import cn.itheima.dao.system.ISysLogDao;
import cn.itheima.dao.system.IUserDao;
import cn.itheima.domain.cargo.Export;
import cn.itheima.domain.cargo.ExportProduct;
import cn.itheima.domain.cargo.ExportProductExample;
import cn.itheima.utils.FileUpLoadUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ExportProductDao exportProductDao;

    @Autowired
    private ExportDao exportDao;

    @Autowired
    private StatDao statDao;


    @Test
    public void findAll() throws IOException {
        List<Map> list = statDao.findBySysLoad("1");
        for (Map<String, String> map : list) {
            System.out.println(map);
        }
    }

    @Test
    public void test() throws IOException {
        Export export = exportDao.selectByPrimaryKey("bb706650-0a56-4311-b0bd-212dfd239921");
        for (ExportProduct exportProduct : export.getExportProducts()) {
            System.out.println(exportProduct);
        }
    }


}