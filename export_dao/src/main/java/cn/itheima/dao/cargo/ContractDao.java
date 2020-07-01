package cn.itheima.dao.cargo;

import cn.itheima.domain.cargo.Contract;
import cn.itheima.domain.cargo.ContractProductVo;
import com.sun.corba.se.pept.transport.ContactInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractDao {
    int deleteByPrimaryKey(String id);

    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);

    List<Contract> findAll();

    List<Contract> findByCreatePerson(String createBy);

    List<Contract> findByCreateDeptId(String deptId);

    List<ContractProductVo> findByShipDate(@Param("shipDate") String date,@Param("companyId") String companyId);
}