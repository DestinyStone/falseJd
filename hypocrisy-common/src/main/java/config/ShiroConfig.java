package config;


import filter.JWTFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shiro.CustomModularRealmAuthenticator;
import shiro.factory.NoSessionsSubjectFactory;
import shiro.realm.JWTRealm;
import shiro.realm.UsernamePasswordRealm;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/9 14:16
 * @Description: shior配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 整合aop代理
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 开启注解
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * shiro过滤器
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        LinkedHashMap<String, Filter> fliterMap = new LinkedHashMap<String, Filter>();
        fliterMap.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(fliterMap);

        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("/login/**", "anon");
        stringStringHashMap.put("/errorHandler/**", "anon");
        stringStringHashMap.put("/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(stringStringHashMap);

        this.dynamicConfigFilterUrl(shiroFilterFactoryBean);

        return shiroFilterFactoryBean;
    }

    private void dynamicConfigFilterUrl(ShiroFilterFactoryBean shiroFilterFactoryBean) {

    }

    @Bean
    public DefaultSubjectDAO subjectDAO() {
        DefaultSubjectDAO defaultSubjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        defaultSubjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        return defaultSubjectDAO;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("usernamePasswordRealm") UsernamePasswordRealm realm,
                                                               @Qualifier("jwtRealm") JWTRealm jwtRealm,
                                                               @Qualifier("subjectDAO") DefaultSubjectDAO defaultSubjectDAO) {

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        /** 关闭自带的session 及session仓库 **/
        defaultWebSecurityManager.setSubjectFactory(new NoSessionsSubjectFactory());
        defaultWebSecurityManager.setSubjectDAO(defaultSubjectDAO);

        // 当个token只由单个realm触发
        defaultWebSecurityManager.setAuthenticator(new CustomModularRealmAuthenticator());
        defaultWebSecurityManager.setRealms(Arrays.asList(realm, jwtRealm));
        return defaultWebSecurityManager;
    }

    @Bean
    public UsernamePasswordRealm usernamePasswordRealm() {
        UsernamePasswordRealm usernamePasswordRealm = new UsernamePasswordRealm();
        return usernamePasswordRealm;
    }

    @Bean
    public JWTRealm jwtRealm() {
        JWTRealm jwtRealm = new JWTRealm();
        return jwtRealm;
    }

}
