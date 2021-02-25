package utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/17 20:50
 * @Description:
 */
public class RedisKeyRule {
    public static String prefix = "verity_";
    public static int expire = 1 * 60 * 10;   // 10 分钟

    public static String verityKeyRule(String email, String phone) throws IllegalAccessException {
        String name = null;
        if (!StringUtils.isBlank(email))
            name = email;
        if (!StringUtils.isBlank(phone))
            name = phone;

        if (name == null)
            throw new IllegalAccessException("email 和 phone 参数不能同时为空");

        return prefix + name;
    }
}
