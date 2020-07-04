package cn.itheima.web.controller.system;

import cn.itheima.dao.cargo.ExportDao;
import cn.itheima.domain.cargo.Export;
import cn.itheima.domain.cargo.ExportResult;
import cn.itheima.domain.cargo.ExportVo;
import cn.itheima.domain.system.Role;
import cn.itheima.service.IUserService;
import cn.itheima.service.cargo.ExportService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext-dao.xml"})
public class UserControllerTest {

    @Autowired
    private ExportDao exportDao;


    @Test
    public void changRole() {
        Export export = exportDao.selectByPrimaryKey("94a56e29-839f-48ba-8a23-8261b514d8e5");
        ExportVo exportVo = new ExportVo();

        BeanUtils.copyProperties(export,exportVo);
        exportVo.setExportId(export.getId());

        WebClient.create("http://localhost:9090/ws/export/ep").post(exportVo);

        ExportResult exportResult = WebClient.create("http://localhost:9090/ws/export/ep/94a56e29-839f-48ba-8a23-8261b514d8e5").get(ExportResult.class);

        System.out.println(exportResult);
    }
}