package cn.itheima.service.cargo.impl;

import cn.itheima.dao.cargo.ContractDao;
import cn.itheima.dao.cargo.ContractProductDao;
import cn.itheima.dao.cargo.ExtCproductDao;
import cn.itheima.domain.cargo.Contract;
import cn.itheima.domain.cargo.ContractProduct;
import cn.itheima.domain.cargo.ExtCproduct;
import cn.itheima.service.cargo.ContractProductService;
import cn.itheima.service.cargo.ContractService;
import cn.itheima.service.cargo.ExtCproductService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-28 15:27
 **/

@Service
public class ExtCproductServiceImpl implements ExtCproductService {

    @Autowired
    private ExtCproductDao extCproductDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ContractProductDao contractProductDao;

    /**
     * @param id
     * 删除extCproduct（附件） 需要更新对应的contract
     */
    @Override
    public void deleteById(String id) {

        ExtCproduct extCproduct = extCproductDao.selectByPrimaryKey(id);

        double money=extCproduct.getAmount();

        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());

        contract.setTotalAmount(contract.getTotalAmount()-money);

        contract.setExtNum(contract.getExtNum()-1);

        contractDao.updateByPrimaryKeySelective(contract);

        extCproductDao.deleteByPrimaryKey(id);

    }

    @Override
    public void deleteByContractId(String contractId) {
        extCproductDao.deleteByContractId(contractId);
    }

    @Override
    public void deleteByContractProductId(String contractProductId) {
        extCproductDao.deleteByContractProductId(contractProductId);
    }

    @Override
    public void save(ExtCproduct record) {

        double money=0;

        if(record.getPrice()!=null&&record.getCnumber()!=null){
            money=record.getCnumber()*record.getPrice();
        }

        record.setAmount(money);

        extCproductDao.insertSelective(record);

        Contract contract = contractDao.selectByPrimaryKey(record.getContractId());

        contract.setTotalAmount(contract.getTotalAmount()+money);
        contract.setExtNum(contract.getExtNum()+1);

        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public ExtCproduct findById(String id) {
        return extCproductDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(ExtCproduct record) {

         double money_before=extCproductDao.selectByPrimaryKey(record.getId()).getAmount();

         double money_after=0;

         if(record.getPrice()!=null&&record.getCnumber()!=null){
             money_after=record.getCnumber()*record.getPrice();
         }

         record.setAmount(money_after);

        Contract contract = contractDao.selectByPrimaryKey(record.getContractId());

        contract.setTotalAmount(contract.getTotalAmount()+money_after-money_before);

        contractDao.updateByPrimaryKeySelective(contract);

        extCproductDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<ExtCproduct> findByContractProductId(String contractProductId) {
        return extCproductDao.findByContractProductId(contractProductId);
    }


    @Override
    public PageInfo<ExtCproduct> findByPage(Integer currentPage, Integer pageSize, String contractProductId) {

        PageHelper.startPage(currentPage,pageSize);

        List<ExtCproduct> list = extCproductDao.findByContractProductId(contractProductId);

        return new PageInfo<>(list,8);
    }
}
