package annon;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import utils.RedisUtils;

import java.lang.annotation.*;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/19 12:55
 * @Description: 导入 common 包中 一些必要的组件
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(RedisUtils.class)
@EnableFeignClients(basePackages = "feign.service")
public @interface ImportCommon {
}
