package cn.itheima.web.realm;

import cn.itheima.domain.system.Module;
import cn.itheima.domain.system.User;
import cn.itheima.service.IModuleService;
import cn.itheima.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-26 08:19
 **/

@Component
public class SaasRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    private IModuleService moduleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("authorization....授权");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        User user = (User)principalCollection.getPrimaryPrincipal();

        List<Module> moduleList = moduleService.findModulesByUser(user);

        for (Module module : moduleList) {
            authorizationInfo.addStringPermission(module.getCPermission());
        }

        return null;
    }

    /**
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     *
     * 当调用Subject.login(Authentication)时会经过此方法 可自定义登录过程的逻辑 成功则返回 authenticationInfo
     * 并可以在subject中获取对应的 authenticationInfo 信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("authentication....认证");

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        String email = token.getUsername();

        String  password =new String(token.getPassword());

        User user = userService.findUserByUsernameAndPassword(email, password);

        if (user!=null){
            return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        }

        return null;
    }
}
