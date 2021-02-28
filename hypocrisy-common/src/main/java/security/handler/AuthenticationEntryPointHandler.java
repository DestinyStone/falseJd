package security.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import response.Message;
import response.type.ResponseCodeType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/25 12:56
 * @Description:
 */
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        Message message = new Message(ResponseCodeType.PARAM_ERROR, "用户名或密码错误", false);
        httpServletResponse.getWriter().write(JSONObject.toJSONString(message));
        httpServletResponse.getWriter().flush();
    }
}
