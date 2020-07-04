package cn.itheima.service.cargo.impl;

import cn.itheima.dao.cargo.ExportDao;
import cn.itheima.dao.cargo.ExportProductDao;
import cn.itheima.dao.cargo.ExtEproductDao;
import cn.itheima.domain.cargo.*;
import cn.itheima.service.cargo.ExportProductService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-07-02 11:36
 **/

@Service
public class ExportProductServiceImpl implements ExportProductService {

    @Autowired
    private ExportProductDao exportProductDao;

    @Autowired
    private ExportDao exportDao;

    @Autowired
    private ExtEproductDao extEproductDao;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int deleteById(String id) {
        ExportProduct exportProduct = exportProductDao.selectByPrimaryKey(id);

        String exportId = exportProduct.getExportId();
        Export export = exportDao.selectByPrimaryKey(exportId);//通过货物对象查询到对应的export对象
        export.setProNum(export.getProNum()-1);//更新export货物数量

        ExtEproductExample eproductExample = new ExtEproductExample();
        eproductExample.createCriteria().andExportProductIdEqualTo(id);
        List<ExtEproduct> extEproducts = extEproductDao.selectByExample(eproductExample);

        for (ExtEproduct extEproduct : extEproducts) {
            extEproductDao.deleteByPrimaryKey(extEproduct.getId());//删除货物对应的附件
        }

        export.setExtNum(export.getExtNum()-extEproducts.size());//更新export附件书数量

        return exportProductDao.deleteByPrimaryKey(id);//删除货物
    }

    @Override
    public int insert(ExportProduct record) {

        return 0;
    }

    @Override
    public int save(ExportProduct record) {
        return 0;
    }

    @Override
    public List<ExportProduct> findByExample(ExportProductExample example) {
        return exportProductDao.selectByExample(example);
    }

    @Override
    public PageInfo<ExportProduct> findByExampleAndPage(Integer currentPage, Integer pageSize, ExportProductExample example) {

        PageHelper.startPage(currentPage,pageSize);
        List<ExportProduct> list = exportProductDao.selectByExample(example);

        return new PageInfo<>(list,8);
    }

    @Override
    public ExportProduct findById(String id) {
        return exportProductDao.selectByPrimaryKey(id);
    }

    @Override
    public int update(ExportProduct record) {
        return 0;
    }
}
