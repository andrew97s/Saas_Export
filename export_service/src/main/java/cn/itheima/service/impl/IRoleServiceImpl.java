package cn.itheima.service.impl;

import cn.itheima.dao.system.IModuleDao;
import cn.itheima.dao.system.IRoleDao;
import cn.itheima.domain.system.Module;
import cn.itheima.domain.system.Role;
import cn.itheima.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-23 10:00
 **/
@Service
public class IRoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IModuleDao moduleDao;

    @Override
    public List<Role> findAllRoles(String companyId) {
        return roleDao.findAll(companyId);
    }

    @Override
    public Role findRoleById(String roleId, String companyId) {
        return roleDao.findById(roleId,companyId);
    }

    @Override
    public void deleteRoleById(String id) {
        roleDao.delete(id);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.update(role);
    }

    @Override
    public void addRole(Role role) {
        roleDao.add(role);
    }

    @Override
    public List<Role> findRolesByUserId(String userId) {
        return roleDao.findByUserId(userId);
    }

    @Override
    public PageInfo<Role> findByPage(Integer currentPage, Integer pageSize,String companyId) {

        PageHelper.startPage(currentPage,pageSize);

        List<Role> list = roleDao.findAll(companyId);

        return new PageInfo<>(list,8);
    }

    @Override
    public List<Module> findModulesByRoleId(String roleId) {

        return moduleDao.findByRoleId(roleId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateRoleModule(String roleId, String[] moduleIds) {

        moduleDao.deleteModuleByRoleId(roleId);

        if (moduleIds!=null&&moduleIds.length>0){

            moduleDao.addModuleForRole(roleId,moduleIds);
        }

    }
}
