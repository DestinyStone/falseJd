package shiro.realm;

import bean.UmsMember;
import feign.service.FeignUmsMemberService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/9 14:16
 * @Description: 针对UsernamePasswordToken校验的Realm
 */

public class UsernamePasswordRealm extends AuthorizingRealm {

    @Autowired(required = false)
    private FeignUmsMemberService feignUmsMemberService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String permission = feignUmsMemberService.selectMemberPermission(((UmsMember)principals.getPrimaryPrincipal()).getPermissionId());
        if (permission == null)
            return null;
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission(permission);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        final String username = token.getPrincipal().toString();
        UmsMember umsMemberResult = feignUmsMemberService.selectByUsername(username);
        if (umsMemberResult == null) {
            throw new UnknownAccountException("未知的用户");
        }
        return new SimpleAuthenticationInfo(umsMemberResult, umsMemberResult.getPassword(), this.getName());
    }
}
