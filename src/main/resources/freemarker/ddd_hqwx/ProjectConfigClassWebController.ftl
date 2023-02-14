package ${_configCommand.packageMainPath}.${_configClass.configClassPackagePath}.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * Web请求鉴权拦截器等配置
 * create time: ${_currentTime}
 */
@Configuration
public class WebControllerConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    /**
     * 添加自定义拦截器
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //TODO 添加鉴权拦截器

        // 管理端请求访问鉴权拦截器
        // registry.addInterceptor(new AuthAdminInterceptor()).addPathPatterns("/admin/**");
        // >> 或者使用公共定义的 SecurityInterceptor bean, 使用前需要使用 @Bean 创建对应的bean
        // ApplicationContext ctx = getApplicationContext();
        // if (Objects.isNull(ctx)) {
        //     throw new RuntimeException("applicationContext is null");
        // }
        // SecurityInterceptor securityInterceptor = ctx.getBean(SecurityInterceptor.class);
        // registry.addInterceptor(securityInterceptor).addPathPatterns("/admin/**");


        // C端请求访问鉴权拦截器
        // registry.addInterceptor(new AuthAppInterceptor()).addPathPatterns("/app/**");

        super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**").allowedMethods("*").allowedOrigins("*").allowedHeaders("*");
    }

}
