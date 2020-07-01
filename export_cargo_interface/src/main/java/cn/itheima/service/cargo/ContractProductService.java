package cn.itheima.service.cargo;

import cn.itheima.domain.cargo.ContractProduct;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-28 08:32
 **/
public interface ContractProductService {

    void deleteById(String id);

    void deleteByContractId(String contractId);

    void save(ContractProduct record);

    void saveList(List<ContractProduct> record);

    ContractProduct findById(String id);

    void update(ContractProduct record);

    PageInfo<ContractProduct> findByPage(Integer currentPage,Integer pageSize,String contractId);
}
