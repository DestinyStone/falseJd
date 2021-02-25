package annon;

import config.WebSecurityConfig;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.lang.annotation.*;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/24 11:45
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WebSecurityConfig.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableRedisHttpSession
public @interface EnableSecurity {
}
