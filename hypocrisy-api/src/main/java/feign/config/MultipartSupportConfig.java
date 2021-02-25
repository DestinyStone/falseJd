package feign.config;

import feign.Contract;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/20 02:00
 * @Description:
 */
@Configuration
public class MultipartSupportConfig implements RequestInterceptor {


    /**
     * 启用feigin自定义注解支持，如 @RequestLine 和 @Param
     * @return
     */
    @Bean
    public Contract feignContract(){
        return new Contract.Default();
    }

    /**
     * feign 实现多pojo传输与MultipartFile上传 编码器，需配合开启feign自带注解使用
     * @return
     */
//    @Bean
//    public Encoder feignSpringFormEncoder(){
//        //注入自定义编码器
//        return new FeignSpringFormEncoder();
//    }

    /**
     * 解决服务直接调用请求头不传递的问题
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        //解决不传递请求头中的token
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null){
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            //可以在这里将自定义请求头传递进去， key 请求， value 值
            //处理上游请求头信息，传递时继续携带
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                template.header(name, values);
            }
        }

        // 解决seata的xid未传递
        String xid = RootContext.getXID();
        template.header(RootContext.KEY_XID, xid);
    }
}