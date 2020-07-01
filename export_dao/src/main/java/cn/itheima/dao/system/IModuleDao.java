package cn.itheima.dao.system;

import cn.itheima.domain.system.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p>dao of module</p>
 *
 * @author : Andrew
 * @date : 2020-06-23 14:44
 **/
public interface IModuleDao {

    List<Module> findAll();

    Module findById(String id);

    List<Module> findByRoleId(String roleId);

    List<Module> findByUserId(String userId);

    /**
     * @param belong
     * @return 针对Saas&企业管理员 采用belong属性查询对应模块权限
     */
    List<Module> findByBelong(String belong);

    void update(Module module);

    void delete(String  id);

    void add(Module module);

    void deleteModuleByRoleId(String roleId);

    void addModuleForRole(@Param("roleId") String roleId,@Param("moduleIds") String[] moduleIds);

}
