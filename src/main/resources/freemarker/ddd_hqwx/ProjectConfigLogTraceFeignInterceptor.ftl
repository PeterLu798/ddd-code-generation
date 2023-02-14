package ${_configCommand.packageMainPath}.${_configClass.configClassPackagePath}.log;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * Feign请求跟踪拦截器
 * create time: ${_currentTime}
 */
@Slf4j
@Component
public class FeignTraceInterceptor implements RequestInterceptor {

    public void apply(RequestTemplate requestTemplate) {
        if(requestTemplate != null) {
            String traceId = MDC.get("traceId");
            if (StringUtils.isNotBlank(traceId)) {
                requestTemplate.header("traceId", traceId);
            }
        }
    }
}