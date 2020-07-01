package cn.itheima.service.cargo.impl;

import cn.itheima.dao.cargo.ContractProductDao;
import cn.itheima.domain.cargo.ContractProduct;
import cn.itheima.service.cargo.ContractProductService;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-dubbo.xml")*/

public class ContractProductServiceImplTest {

   /* @Autowired
    private ContractProductDao contractProductDao;*/

    @Test
    public void findById() {

        Configuration configuration = new Configuration(Zone.autoZone());

        UploadManager uploadManager = new UploadManager(configuration);


        String accessKey="fnSQoG4VkEMqwp16eBm7cI8nMMx7gwb8hytkAPKA";

        String secrectKey="BcluojCUbwgR6LYDxcll9Xrv_TeH1xv3fl_rZ0We";

        String bucket="dwqdqw";

        String localFilePath="C:\\Users\\Administrator\\Desktop\\04-search_03.jpg";

        String key="fileTest";

        Auth auth = Auth.create(accessKey, secrectKey);

        String token = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath,key,token);

            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            System.out.println(putRet.key);

            System.out.println("http://q8crcz9ob.bkt.clouddn.com/"+putRet.hash);

        } catch (QiniuException e) {
            e.printStackTrace();

            Response r = e.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

    }

    public static void main(String[] args) {

    }
}