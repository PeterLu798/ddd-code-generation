package ${_configCommand.packageMainPath}.${_configClass.configClassPackagePath}.log;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Controller请求日志跟踪打印拦截器
 * create time: ${_currentTime}
 */
@Slf4j
@Aspect
@Order(1)
@Component
@EnableAsync
public class ControllerTraceInterceptor {
    // @Autowired
    // private RedisTemplate<String,String> redisTemplate;
    private static final String PROMOTE_MEM_ALERT_KEY = "PROMOTE_MEM_ALERT_KEY";

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object aroundMethod(ProceedingJoinPoint pjd) throws Throwable {
        Object result = null;
        // String className = pjd.getTarget().getClass().getName();
        // MethodSignature signature = (MethodSignature) pjd.getSignature();
        // Method method = signature.getMethod();
        // String methodName = signature.getName();

        long startTime = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURI();
        String params = null;

        try {
            params = getParam(request, pjd.getArgs());

            // log.info("Processed url: {} start with contextId:{} IP:{} by User:{} by param:{}",
            //    url, contextId, IPUtils.getIpAddr(request), name, params);
            log.info("Processed url: {} start with IP: {} by param: {}", url, this.getIpAddr(request), params);
            long size = 0;
            try {
                size = getLimitSize(request, pjd.getArgs());
            } catch (Exception e){
                log.error("参数拦截失败:",e);
            }
            if (size > 200000){
                throw new RuntimeException("分页页数过大，请减少分页或减少一页显示数量");
            }
            result = pjd.proceed();

            // 方法正常-无异常执行后 打出成功日志
            // log.info("Processed url: {} success with contextId：{} result:{}", url, contextId, result);
        }
        catch (Throwable throwable) {
            long cost = System.currentTimeMillis() - startTime;
            // 统一捕获非受控异常 记录失败日志 填充返回对象 避免异常继续上抛影响前端
            // log.info("Processed url: {} cost: {} failed with contextId: {}, exception :{}", url, cost, contextId, throwable.toString());
            log.info("Processed url: {} cost: {} failed with exception :{}", url, cost, throwable.toString());
            throw throwable;
        }

        // 显示JVM总内存
        long totalMem = Runtime.getRuntime().totalMemory();
        // 显示JVM尝试使用的最大内存
        long maxMem = Runtime.getRuntime().maxMemory();
        // 空闲内存
        long freeMem = Runtime.getRuntime().freeMemory();

        int threadcount = ManagementFactory.getThreadMXBean().getThreadCount();
        int retSize = 0;
        if (result != null){
            retSize = result.toString().length();
        }
        long cost = System.currentTimeMillis() - startTime;

        log.info("Completed url: {} cost: {} size: {} Current Thread count: {} max: {} allocated: {} used: {} free: {}",
                url,
                cost + "ms",
                retSize,
                threadcount,
                // heapMemoryUsage, nonHeapMemoryUsage,
                maxMem / 1024 / 1024 + "MB",
                totalMem / 1024 / 1024 + "MB",
                (totalMem - freeMem) / 1024 / 1024 + "MB",
                (maxMem - totalMem + freeMem) / 1024 / 1024 + "MB");

        if (((maxMem - totalMem + freeMem) / 1024 / 1024) < 100) {
            // 升级内存告警
            // Object obj = redisTemplate.opsForValue().get(PROMOTE_MEM_ALERT_KEY);
            // send message to Weixin
        }

        return result;
    }

    private String getParam(HttpServletRequest request, Object[] args) throws IOException {
        if(StringUtils.isNotBlank(request.getContentType())
                && request.getContentType().toLowerCase().contains("json")){
            String submitMehtod = request.getMethod();

            if (submitMehtod.equalsIgnoreCase("GET")) {
                if (StringUtils.isNotBlank(request.getQueryString())) {
                    return new String(request.getQueryString().getBytes("iso-8859-1"), "utf-8").replaceAll("%22", "\"");
                }
                else {
                    return "{}";
                }

            } else {
                // POST
                if (args != null && args.length > 0) {
                    Stream<?> stream = ArrayUtils.isEmpty(args) ? Stream.empty() : Arrays.asList(args).stream();
                    List<Object> logArgs = stream
                            .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                            .collect(Collectors.toList());
                    //过滤后序列化无异常
                    return JSONObject.toJSONString(logArgs);
                } else {
                    return "{}";
                }
            }
        } else {
            return JSONObject.toJSONString(request.getParameterMap());
        }
    }

    private Long getLimitSize(HttpServletRequest request, Object[] args){
        if (StringUtils.isNotBlank(request.getContentType())
                && request.getContentType().toLowerCase().contains("json")){
            //TODO 暂时不处理
            return 0L;
        } else {
            String pnStr = request.getParameter("pageNo");
            String pnSize = request.getParameter("pageSize");
            if (StringUtils.isNotBlank(pnStr) && StringUtils.isNotBlank(pnSize)) {
                long limitSize = 0;
                try{
                    limitSize = Long.parseLong(pnStr)*Long.parseLong(pnSize);
                    return limitSize;
                }catch (Exception e){
                    return 0L;
                }
            }
        }
        return 0L;
    }

    // 可以将 getIpAddr 移动公共的工具类中去
    private String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception err) {
            log.warn("getIpAddr error ", err);
        }

        return ip;
    }
}
