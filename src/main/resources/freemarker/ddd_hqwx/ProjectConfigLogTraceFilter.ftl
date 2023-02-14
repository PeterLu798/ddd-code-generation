package ${_configCommand.packageMainPath}.${_configClass.configClassPackagePath}.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 日志跟踪过滤器
 * create time: ${_currentTime}
 */
@Slf4j
// @ConditionalOnClass(Filter.class)
@Configuration
public class LogTraceFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;

            String traceId = request.getHeader("traceId");
            if (StringUtils.isBlank(traceId)) {
                traceId = UUID.randomUUID().toString();
            }

            // 先移除, 防止线程复用
            MDC.remove("traceId");
            MDC.put("traceId", traceId);

            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // 子线程没结束，上下文会被干掉
            //  MDC.remove("contextId");
        }
    }
}
