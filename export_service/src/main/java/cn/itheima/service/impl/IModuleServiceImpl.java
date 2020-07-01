package cn.itheima.service.impl;

import cn.itheima.dao.system.IModuleDao;
import cn.itheima.domain.system.Module;
import cn.itheima.domain.system.User;
import cn.itheima.service.IModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-23 15:04
 **/

@Service
public class IModuleServiceImpl implements IModuleService {

    @Autowired
    private IModuleDao moduleDao;

    @Override
    public List<Module> findAllModule() {
        return moduleDao.findAll();
    }

    @Override
    public List<Module> findModulesByUser(User user) {

        if (user.getDegree().equals("0")||user.getDegree().equals("1")){
           return moduleDao.findByBelong(String.valueOf(user.getDegree()));
        }

        return moduleDao.findByUserId(user.getId());
    }

    @Override
    public Module findModuleById(String id) {
        return moduleDao.findById(id);
    }

    @Override
    public List<Module> findModulesByRoleId(String roleId) {
        return moduleDao.findByRoleId(roleId);
    }

    @Override
    public void updateModule(Module module) {
        moduleDao.update(module);
    }

    @Override
    public void deleteModule(String id) {
        moduleDao.delete(id);
    }

    @Override
    public void addModule(Module module) {
        moduleDao.add(module);
    }

    @Override
    public PageInfo<Module> findModuleByPage(Integer currentPage, Integer pageSize) {

        PageHelper.startPage(currentPage,pageSize);

        List<Module> list = moduleDao.findAll();

        return new PageInfo<>(list,8);
    }
}
