package cn.itheima.web.controller.system;

import cn.itheima.domain.system.Role;
import cn.itheima.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext_service.xml")
public class UserControllerTest {

    @Autowired
    private IUserService userService;

    @Test
    public void changRole() {
        userService.addRolesForUser("002108e2-9a10-4510-9683-8d8fd1d374ef",new String[]{"4028a1cd4ee2d9d6014ee2df4c6a0002","4028a1cd4ee2d9d6014ee2df4c6a0003"});
        List<Role> roles = userService.findRolesByUserId("002108e2-9a10-4510-9683-8d8fd1d374ef");
        for (Role role : roles) {
            System.out.println(role);
        }
    }
}