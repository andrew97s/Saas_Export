package cn.itheima.service.cargo.impl;

import cn.itheima.dao.cargo.ContractDao;
import cn.itheima.dao.cargo.ContractProductDao;
import cn.itheima.dao.cargo.ExtCproductDao;
import cn.itheima.domain.cargo.Contract;
import cn.itheima.domain.cargo.ContractProductVo;
import cn.itheima.service.cargo.ContractService;
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
 * @date : 2020-06-27 17:40
 **/

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ExtCproductDao extCproductDao;

    @Autowired
    private ContractProductDao contractProductDao;


    /**
     * @param id
     * @return contract 下 对应有 contractProduct&extCproduct 都应该在contract被删除后删除
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int deleteById(String id) {

        extCproductDao.deleteByContractId(id);

        contractProductDao.deleteByContractId(id);

        return contractDao.deleteByPrimaryKey(id);
    }

    @Override
    public int save(Contract record) {
        return contractDao.insertSelective(record);
    }

    @Override
    public Contract findById(String id) {
        return contractDao.selectByPrimaryKey(id);
    }

    @Override
    public int update(Contract record) {
        return contractDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Contract> findAll() {
        return contractDao.findAll();
    }

    @Override
    public PageInfo<Contract> findByPage(Integer currentPage, Integer pageSize) {

        PageHelper.startPage(currentPage,pageSize);

        List<Contract> list = contractDao.findAll();

        return new PageInfo<>(list,8);
    }

    @Override
    public PageInfo<Contract> findByCreatePerson(Integer currentPage,Integer pageSize,String createBy) {

        PageHelper.startPage(currentPage,pageSize);

        List<Contract> list = contractDao.findByCreatePerson(createBy);


        return new PageInfo<>(list,8);
    }

    @Override
    public PageInfo<Contract> findByCreateDeptId(Integer currentPage,Integer pageSize,String deptId) {
        PageHelper.startPage(currentPage,pageSize);

        List<Contract> list = contractDao.findByCreateDeptId(deptId);

        return new PageInfo<>(list,8);
    }

    @Override
    public List<ContractProductVo> findContractProductVoiByShipTime(String date, String companyId) {
        return contractDao.findByShipDate(date,companyId);
    }
}
