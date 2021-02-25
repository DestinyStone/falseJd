package com.hypocrisy.maven.hypocirsyitem.cache.annon;

import java.lang.annotation.*;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/18 15:48
 * @Description: 开启缓存   缓存key: 业务模块名: 业务类名: 业务关键字段: 业务关键字段值
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EnableBasicCache {
    String defaultFrist() default "hypocrisy_item";
    String prefix();
    String privateKey();
}
