package utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/18 16:20
 * @Description:
 */
public class RedisUtils {
    private static StringRedisTemplate redisTemplate;

    public static String get(String key) {
       return redisTemplate.opsForValue().get(key);
    }

    public static void set(String key, String message, Long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, message, time, timeUnit);
    }

    public static Long increment(String key) {
        return  redisTemplate.opsForValue().increment(key);
    }

    @Autowired
    private void setRedisTemplate(StringRedisTemplate redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

}
