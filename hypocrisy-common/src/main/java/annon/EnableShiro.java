package annon;

import config.ShiroConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 17:32
 * @Description:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ShiroConfig.class)
public @interface EnableShiro {
}
