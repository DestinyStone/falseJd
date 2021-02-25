package com.hypocrisy.maven.hypocirsyitem.cache.aop;

import com.alibaba.fastjson.JSONObject;
import com.hypocrisy.maven.hypocirsyitem.cache.annon.EnableBasicCache;
import utils.RedisUtils;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import response.Message;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/18 15:49
 * @Description:
 */
@Aspect
@Component
public class BasicCacheAop {

    @Autowired
    private RedissonClient redissonClient;

    @Pointcut("@annotation(com.hypocrisy.maven.hypocirsyitem.cache.annon.EnableBasicCache)")
    public void pointcut(){}

    @Around("pointcut() && @annotation(basicCache)")
    public Message around(ProceedingJoinPoint joinPoint, EnableBasicCache basicCache) throws Throwable {
        String cacheKey = genCacheKey(joinPoint, basicCache);

        String result = RedisUtils.get(cacheKey);

        // 第一次判断 是否有缓存
        if (result == null) {
            String lockKey = "lock" + cacheKey;
            RLock lock = redissonClient.getLock(lockKey);
            try {
                lock.lock();
                result = RedisUtils.get(cacheKey);
                if (result == null) {
                    Message proceed = (Message)joinPoint.proceed();
                    RedisUtils.set(cacheKey, JSONObject.toJSONString(proceed), 10L, TimeUnit.HOURS);
                    return proceed;
                }
            }catch (Exception e) {
                e.printStackTrace();
                throw e;
            }finally {
                if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }

        }

        Message message = JSONObject.parseObject(result, Message.class);
        return message;
    }

    private String genCacheKey(ProceedingJoinPoint joinPoint, EnableBasicCache basicCache) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        int index = ArrayUtils.indexOf(parameterNames, basicCache.privateKey());

        if (index == -1) {
            throw new RuntimeException(basicCache.privateKey() + "不存在参数列表上");
        }

        String primaryKeyValue = joinPoint.getArgs()[index].toString();
        return basicCache.defaultFrist() + ":" + basicCache.prefix() + ":" + basicCache.privateKey() + ":" + primaryKeyValue;
    }
}
