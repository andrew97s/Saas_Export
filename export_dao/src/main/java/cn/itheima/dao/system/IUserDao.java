package cn.itheima.dao.system;

import cn.itheima.domain.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p>dao of user</p>
 *
 * @author : Andrew
 * @date : 2020-06-22 21:17
 **/
public interface IUserDao {

    List<User> findAll();

    User findById(String id);

    void add(User user);

    void delete(String  id);

    void update(User user);

    void addRoles(@Param("userId") String userId,@Param("roleIds") String[] roleIds);

    void deleteRoles(String userId);

    User findUserByUsernameAndPassword(@Param("email") String email,@Param("password") String password);
}
