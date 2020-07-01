package cn.itheima.service.impl;

import cn.itheima.dao.system.IRoleDao;
import cn.itheima.dao.system.IUserDao;
import cn.itheima.domain.system.Role;
import cn.itheima.domain.system.User;
import cn.itheima.service.IUserService;
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
 * @date : 2020-06-23 09:20
 **/

@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<User> findAllUser() {

        return userDao.findAll();
    }

    @Override
    public User findUserById(String id) {
        return userDao.findById(id);
    }

    @Override
    public void addUser(User user) {
        userDao.add(user);
    }

    @Override
    public void deleteUserById(String id) {
        userDao.delete(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public PageInfo<User> findBYPage(Integer currentPage, Integer pageSize) {

        PageHelper.startPage(currentPage,pageSize);

        List<User> list = userDao.findAll();

        return new PageInfo<>(list, 8);
    }

    @Override
    public List<Role> findRolesByUserId(String userId) {
        return roleDao.findByUserId(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addRolesForUser(String userId, String[] roleIds) {

        userDao.deleteRoles(userId);

        if (roleIds!=null&&roleIds.length>0){
            userDao.addRoles(userId,roleIds);
        }
    }

    @Override
    public User findUserByUsernameAndPassword(String email, String password) {
        return userDao.findUserByUsernameAndPassword(email,password);
    }
}
