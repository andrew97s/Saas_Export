package cn.itheima.service.cargo;

import cn.itheima.domain.cargo.Contract;
import cn.itheima.domain.cargo.ContractProductVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-27 18:04
 **/
public interface ContractService {
    int deleteById(String id);

    int save(Contract record);

    Contract findById(String id);

    int update(Contract record);

    List<Contract> findAll();

    PageInfo<Contract> findByPage(Integer currentPage,Integer pageSize);

    PageInfo<Contract> findByCreatePerson(Integer currentPage,Integer pageSize,String createBy);

    PageInfo<Contract> findByCreateDeptId(Integer currentPage,Integer pageSize,String deptId);

    List<ContractProductVo> findContractProductVoiByShipTime(String date,String companyId);

}
