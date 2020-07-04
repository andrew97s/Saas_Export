package cn.itheima.service.cargo.impl;

import cn.itheima.dao.cargo.*;
import cn.itheima.domain.cargo.*;
import cn.itheima.service.cargo.ExportService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-07-02 11:37
 **/
@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private ContractProductDao contractProductDao;

    @Autowired
    private ExtCproductDao extCproductDao;

    @Autowired
    private ContractDao contractDao;


    @Autowired
    private ExportDao exportDao;

    @Autowired
    private ExportProductDao exportProductDao;

    @Autowired

    private ExtEproductDao extEproductDao;

    @Override
    public Export selectByPrimarykey(String id) {
        Export export = exportDao.findById(id);
        return export;
    }

    @Override
    public int deleteById(String id) {

        return exportDao.deleteByPrimaryKey(id);
    }

    /**
     * @param record
     * @return
     * 出口报运单（包含多个购销合同） 新增需要增加对应的报运单货物&报运单附件
     */
    @Override
    public int save(Export record) {

        String contractIds = record.getContractIds();
        String[]  contractIdArray =contractIds.split(",");

        List<ContractProduct> contractProductList = contractProductDao.findByContractIds(contractIdArray);

        for (ContractProduct contractProduct : contractProductList) {

            ExportProduct exportProduct = new ExportProduct();

            BeanUtils.copyProperties(contractProduct,exportProduct);

            exportProduct.setExportId(record.getId());

            exportProductDao.insertSelective(exportProduct);

            List<ExtCproduct> extCproductList = contractProduct.getExtCproducts();

            for (ExtCproduct extCproduct : extCproductList) {
                ExtEproduct extEproduct = new ExtEproduct();

                BeanUtils.copyProperties(extCproduct,extEproduct);

                extEproduct.setExportId(record.getId());
                extEproduct.setExportProductId(exportProduct.getId());

                extEproductDao.insertSelective(extEproduct);
            }

            List<Contract> contractList = contractDao.findByIds(contractIdArray);

            Integer proNum=0;
            Integer extNum=0;

            StringBuilder sb=new StringBuilder();

            for (Contract contract : contractList) {
                proNum+=contract.getProNum();
                extNum+=contract.getExtNum();
                sb.append(contract.getContractNo()).append(" ");
            }

            record.setExtNum(extNum);
            record.setProNum(proNum);
            record.setCustomerContract(sb.toString());

            exportDao.insertSelective(record);
        }

        return 0;
    }

    @Override
    public List<Export> findByExample(ExportExample example) {
        return null;
    }

    @Override
    public PageInfo<Export> findByExampleAndPage(Integer currentPage, Integer pageSize, ExportExample example) {

        PageHelper.startPage(currentPage,pageSize);

        List<Export> list = exportDao.selectByExample(example);

        return new PageInfo<>(list,8);
    }

    @Override
    public Export findByIds(String id) {

        ExportExample exportExample = new ExportExample();
        exportExample.createCriteria().andIdEqualTo(id);

        List<Export> exports = exportDao.selectByExample(exportExample);

        return exports.get(0);
    }

    @Override
    public int update(Export record) {

        List<ExportProduct> exportProducts = record.getExportProducts();

        if (exportProducts!=null&&!exportProducts.isEmpty()){

            for (ExportProduct exportProduct : exportProducts) {
              exportProductDao.updateByPrimaryKeySelective(exportProduct);//更新export对应product属性
            }
        }

        return exportDao.updateByPrimaryKeySelective(record);
    }

    /**
     * @param exportResult
     * 将海关报运返回的数据更新至相应的对象
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateE(ExportResult exportResult) {
        Export export = exportDao.selectByPrimaryKey(exportResult.getExportId());

        export.setState(exportResult.getState());
        export.setRemark(exportResult.getRemark());

        Set<ExportProductResult> exportResultProducts = exportResult.getProducts();

        for (ExportProductResult exportResultProduct : exportResultProducts) {
            ExportProduct exportProduct = exportProductDao.selectByPrimaryKey(exportResultProduct.getExportProductId());

            exportProduct.setTax(exportResultProduct.getTax());

            exportProductDao.updateByPrimaryKeySelective(exportProduct);//更新报运货物数据（tax）
        }

        exportDao.updateByPrimaryKeySelective(export);//更新报运单数据(state&remark)
    }


}
