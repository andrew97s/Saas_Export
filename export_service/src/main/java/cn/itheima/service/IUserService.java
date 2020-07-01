package cn.itheima.service;

import cn.itheima.domain.system.Role;
import cn.itheima.domain.system.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p>dao of userService</p>
 *
 * @author : Andrew
 * @date : 2020-06-23 09:19
 **/
public interface IUserService {

    List<User> findAllUser();

    User findUserById(String id);

    void addUser(User user);

    void deleteUserById(String  id);

    void updateUser(User user);

    PageInfo<User> findBYPage(Integer currentPage,Integer pageSize);

    List<Role> findRolesByUserId(String userId);

    void addRolesForUser(String userId,String[] roleIds);

    User findUserByUsernameAndPassword(String email,String password);

}
