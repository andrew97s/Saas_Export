package cn.itheima.dao.cargo;

import cn.itheima.domain.cargo.Factory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FactoryDao {
    int deleteByPrimaryKey(String id);

    int insert(Factory record);

    int insertSelective(Factory record);

    Factory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Factory record);

    int updateByPrimaryKey(Factory record);

    List<Factory> findAll();

    List<Factory> findByCtype(String ctype);

    List<Factory> findByCtypeAndName(@Param("factoryName") String factoryName,@Param("cType") String cType);
}