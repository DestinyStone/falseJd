package shiro.realm;


import bean.UmsMember;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import service.UmsMemberService;
import shiro.token.JWTToken;
import utils.JWTUtils;

/**
 * @Auther: ASUS
 * @Date: 2020/10/10 13:28
 * @Description:
 */
public class JWTRealm extends AuthorizingRealm {

    @Autowired(required = false)
    private UmsMemberService umsMemberService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)throws AuthenticationException{
        String tokenStr = token.getPrincipal().toString();
        String username = JWTUtils.decode("token", token.getPrincipal().toString());
        boolean verify = JWTUtils.verify("token", token.getPrincipal().toString());
        if (!verify) {
            throw  new AuthenticationException("无效的token");
        }
        UmsMember umsMember = umsMemberService.selectByUsername(username);
        if (umsMember == null) {
            throw new UnknownAccountException("无效的用户名");
        }
        return new SimpleAuthenticationInfo(umsMember, tokenStr, this.getName());
    }
}
