package cn.itheima.service;

import cn.itheima.domain.system.Module;
import cn.itheima.domain.system.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p>serviceDao of module</p>
 *
 * @author : Andrew
 * @date : 2020-06-23 15:03
 **/
public interface IModuleService {

    List<Module> findAllModule();

    List<Module> findModulesByUser(User user);

    Module findModuleById(String id);

    List<Module> findModulesByRoleId(String roleId);

    void updateModule(Module module);

    void deleteModule(String  id);

    void addModule(Module module);

    PageInfo<Module> findModuleByPage(Integer currentPage,Integer pageSize);
}
