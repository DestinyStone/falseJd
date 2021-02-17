package utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @Auther: DestinyStone
 * @Date: 2020/12/27 21:43
 * @Description:
 */
public class JWTUtils {

    private static final String salt = "7dcc60b7-d789-4196-bf70-6f8db8b2a2c3";
    private static final  Algorithm algorithm = Algorithm.HMAC256(salt.getBytes());

    /**
     * 生成jwt加密字符串
     * @param key
     * @param value
     * @param exprieTime 过期时间， 单位 毫秒
     * @return
     */
    public static String code(String key, String value, long exprieTime) {
        return JWT.create()
                .withClaim(key, value)
                .withExpiresAt(new Date(System.currentTimeMillis() + exprieTime))
                .sign(algorithm);
    }

    /**
     * 验证JWT
     * @param key
     * @param token
     */
    public static boolean verify(String key, String token) {
        String value = decode(key, token);
        JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim(key, value).build();
        try {
            jwtVerifier.verify(token);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 解密JWT
     * @param key
     * @param token
     * @return
     */
    public static String decode(String key, String token) {
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim(key).asString();
    }
}
