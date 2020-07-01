package cn.itheima.service.cargo.impl;

import cn.itheima.dao.cargo.ContractDao;
import cn.itheima.dao.cargo.ContractProductDao;
import cn.itheima.dao.cargo.ExtCproductDao;
import cn.itheima.dao.cargo.FactoryDao;
import cn.itheima.domain.cargo.Contract;
import cn.itheima.domain.cargo.ContractProduct;
import cn.itheima.domain.cargo.ExtCproduct;
import cn.itheima.domain.cargo.Factory;
import cn.itheima.service.cargo.ContractProductService;
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
 * @date : 2020-06-28 08:50
 * contactProduct 该对象从属于contract 因此此类的增删改的动作应与对应的contract一致
 **/
@Service
public class ContractProductServiceImpl implements ContractProductService {

    @Autowired
    private ContractProductDao contractProductDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ExtCproductDao extCproductDao;

    @Autowired
    private FactoryDao factoryDao;

    /**
     * @param id
     * 删除contractProduct 需要同时删除其对应的exProduct(货物附件) 及更新对应contract信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteById(String id) {

        double money=0;

        List<ExtCproduct> extCproductList = extCproductDao.findByContractProductId(id);

        extCproductDao.deleteByContractProductId(id);

        ContractProduct record = contractProductDao.selectByPrimaryKey(id);

        if (record.getCnumber()!=null&&record.getPrice()!=null){
            money  = record.getCnumber()*record.getPrice();
        }

        contractProductDao.deleteByPrimaryKey(id);

        Contract contract = contractDao.selectByPrimaryKey(record.getContractId());

        contract.setTotalAmount(contract.getTotalAmount()-money);

        contract.setProNum(contract.getProNum()-1);

        contract.setExtNum(contract.getExtNum()-extCproductList.size());

        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void deleteByContractId(String contractId) {
        contractProductDao.deleteByContractId(contractId);
    }

    /**
     * @param record
     * @return 保存contractProduct前需手动计算出money 并更新对应contract总金额&货物总量信息。。。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(ContractProduct record) {

        double money=0;

        if (record.getCnumber()!=null&&record.getPrice()!=null){
           money  = record.getCnumber()*record.getPrice();
        }
        record.setAmount(money);

        contractProductDao.insertSelective(record);

        Contract contract = contractDao.selectByPrimaryKey(record.getContractId());

        contract.setTotalAmount(contract.getTotalAmount()+money);

        contract.setProNum(contract.getProNum()+1);

        contractDao.updateByPrimaryKeySelective(contract);

    }

    @Override
    public void saveList(List<ContractProduct> record) {
        for (ContractProduct contractProduct : record) {

            List<Factory> factoryList = factoryDao.findByCtypeAndName(contractProduct.getFactoryName(), "货物");
            //设置factoryId
            if (factoryList!=null&&!factoryList.isEmpty()){
                contractProduct.setFactoryId(factoryList.get(0).getId());
            }

            save(contractProduct);
        }
    }

    @Override
    public ContractProduct findById(String id) {
        return contractProductDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(ContractProduct record) {

        double newMoney=0;

        if (record.getCnumber()!=null&&record.getPrice()!=null){
            newMoney  = record.getCnumber()*record.getPrice();
        }

        record.setAmount(newMoney);

        contractProductDao.updateByPrimaryKeySelective(record);

        ContractProduct ocp = contractProductDao.selectByPrimaryKey(record.getId());//更新前contractProduct对象

        Contract contract = contractDao.selectByPrimaryKey(record.getContractId());

        contract.setTotalAmount(contract.getTotalAmount()+(newMoney-ocp.getAmount()));//更新对应contract 总金额

        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public PageInfo<ContractProduct> findByPage(Integer currentPage, Integer pageSize, String contractId) {

        PageHelper.startPage(currentPage,pageSize);

        List<ContractProduct> list = contractProductDao.findAll(contractId);

        return new PageInfo<>(list,8);
    }
}
