package cn.itheima.dao.cargo;

import cn.itheima.domain.cargo.ExtCproduct;

import java.util.List;

public interface ExtCproductDao {
    void deleteByPrimaryKey(String id);

    void deleteByContractId(String contractId);

    void deleteByContractProductId(String contractProductId);

    void insert(ExtCproduct record);

    void insertSelective(ExtCproduct record);

    ExtCproduct selectByPrimaryKey(String id);

    void updateByPrimaryKeySelective(ExtCproduct record);

    void updateByPrimaryKeyWithBLOBs(ExtCproduct record);

    void updateByPrimaryKey(ExtCproduct record);

    List<ExtCproduct> findAll(String contractId);

    List<ExtCproduct> findByContractProductId(String contractProductId);
}