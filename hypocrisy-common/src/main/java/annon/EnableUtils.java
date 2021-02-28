package annon;

import org.springframework.context.annotation.Import;
import utils.IdUtils;
import utils.properties.SnowFlakProperties;

import java.lang.annotation.*;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 16:22
 * @Description:
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({IdUtils.class, SnowFlakProperties.class})
public @interface EnableUtils {
}
