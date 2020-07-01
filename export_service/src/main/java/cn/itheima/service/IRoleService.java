package cn.itheima.service;

import cn.itheima.domain.system.Module;
import cn.itheima.domain.system.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p>dao of roleService</p>
 *
 * @author : Andrew
 * @date : 2020-06-23 09:59
 **/
public interface IRoleService {

    List<Role> findAllRoles(String companyId);

    Role findRoleById(String roleId,String companyId);

    void deleteRoleById(String id);

    void updateRole(Role role);

    void addRole(Role role);

    List<Role> findRolesByUserId(String userId);

    PageInfo<Role> findByPage(Integer currentPage,Integer pageSize,String companyId);

    List<Module> findModulesByRoleId(String roleId);

    void updateRoleModule(String roleId,String[] moduleIds);
}
