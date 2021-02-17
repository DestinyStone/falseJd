package shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Auther: ASUS
 * @Date: 2020/10/10 13:29
 * @Description:
 */
public class JWTToken implements AuthenticationToken {

    private String token;


    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
