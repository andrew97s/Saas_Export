package cn.itheima.dao.system;

import cn.itheima.domain.system.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p>dao of role</p>
 *
 * @author : Andrew
 * @date : 2020-06-22 21:20
 **/
public interface IRoleDao {

    List<Role> findAll(String companyId);

    Role findById(@Param("roleId") String roleId,@Param("companyId") String companyId);

    void delete(String id);

    void update(Role role);

    void add(Role role);

    List<Role> findByUserId(String userId);

}
