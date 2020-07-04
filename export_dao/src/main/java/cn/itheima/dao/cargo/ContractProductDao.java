package cn.itheima.dao.cargo;

import cn.itheima.domain.cargo.ContractProduct;
import cn.itheima.domain.cargo.ExportProduct;

import java.util.List;

public interface ContractProductDao {
    void deleteByPrimaryKey(String id);

    void deleteByContractId(String contractId);

    void insert(ContractProduct record);

    void insertSelective(ContractProduct record);

    ContractProduct selectByPrimaryKey(String id);

    void updateByPrimaryKeySelective(ContractProduct record);

    void updateByPrimaryKey(ContractProduct record);

    List<ContractProduct> findAll(String contractId);

    List<ContractProduct> findByContractIds(String[] contractIds);
}