package utils;

import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/18 20:15
 * @Description:
 */
public class AccessControlUtils {

    public static void setToken(HttpServletResponse response) {
        response.setHeader("Access-control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers", response.getHeader("Access-Control-Request-Headers"));
        response.setHeader("Access-Control-Expose-Headers", "token");
    }
}
