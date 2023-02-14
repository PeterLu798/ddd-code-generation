package ${_configCommand.packageMainPath}.${_configClass.configClassPackagePath}.mybatisplus;

import com.baomidou.dynamic.datasource.plugin.MasterSlaveAutoRoutingPlugin;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus分页插件、多租户等配置
 * create time: ${_currentTime}
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(${_configCommand.packageMainPath}.${_configClass.configClassPackagePath}.mybatisplus.TenantFilterProperties.class)
public class MyBatisPlusConfig {

    /**
     * 数据源主从自动切换插件
     */
    @Bean
    public MasterSlaveAutoRoutingPlugin masterSlaveAutoRoutingPlugin() {
        return new MasterSlaveAutoRoutingPlugin();
    }

    /**
     * 分页拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(TenantFilterProperties properties) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        TenantLineInnerInterceptor tenantLineInnerInterceptor = new TenantLineInnerInterceptor();
        // 自定义的处理类
        TenantLineHandler tenantHandlerImpl = new TenantHandlerImpl(properties);
        tenantLineInnerInterceptor.setTenantLineHandler(tenantHandlerImpl);

        interceptor.addInnerInterceptor(tenantLineInnerInterceptor);

        return interceptor;
    }

}
