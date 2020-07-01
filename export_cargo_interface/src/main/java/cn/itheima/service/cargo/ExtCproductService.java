package cn.itheima.service.cargo;

import cn.itheima.domain.cargo.ExtCproduct;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-28 08:56
 **/
public interface ExtCproductService {

    void deleteById(String id);

    void deleteByContractId(String contractId);

    void deleteByContractProductId(String contractProductId);

    void save(ExtCproduct record);

    ExtCproduct findById(String id);

    void update(ExtCproduct record);

    List<ExtCproduct> findByContractProductId(String contractProductId);

    PageInfo<ExtCproduct> findByPage(Integer currentPage,Integer pageSize,String contractProductId);
}
