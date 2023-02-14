package ${_configCommand.packageMainPath}.${_configClass.configClassPackagePath}.cache;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

/**
 * 默认使用 JetCache 缓存框架
 * create time: ${_currentTime}
 */
@Configuration
@EnableMethodCache(basePackages = "${_configCommand.packageMainPath}")
@EnableCreateCacheAnnotation
public class CacheConfig {
    
}
