package ${_configCommand.packageMainPath}.${_configClass.configClassPackagePath}.web;

import cn.huanju.edu100.bizplat.base.enums.BaseErrorCodeEnum;
import cn.huanju.edu100.bizplat.base.exception.BaseException;
import cn.huanju.edu100.bizplat.base.exception.DataNotFoundException;
import cn.huanju.edu100.bizplat.base.model.ResponseBean;
import ${_configCommand.packageMainPath}.shared.constants.ProjectConstants;
import com.hqwx.common.security.exception.TokenInvalidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * Web请求异常处理器
 * create time: ${_currentTime}
 */
@RestControllerAdvice(annotations = RestController.class)
public class WebControllerExceptionAdvice {
    private Logger logger = LoggerFactory.getLogger(WebControllerExceptionAdvice.class);

    /**
     * BaseException异常统计捕获，返回给前端标准的ResponseBean
     */
    @ExceptionHandler({BaseException.class, DataNotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseBean<String> handleBaseException(Exception exception) {
        // B0001 系统执行出错, 一级宏观错误码
        return ResponseBean.failed(BaseErrorCodeEnum.SYS_EXEC_ERROR_B0001, ProjectConstants.APP_ID, "", exception.getMessage());
    }

    /**
     * RuntimeException异常统一捕获，返回给前端标准的ResponseBean
     */
    @ExceptionHandler({DataAccessException.class, RuntimeException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean<String> handleRuntimeException(Exception ex) {
        logger.error("Operation Failed", ex);
        String message = (ex instanceof DataAccessException) ? "DataAccessException" : "Operation failed";

        // B0001 系统执行出错, 一级宏观错误码
        return ResponseBean.failed(BaseErrorCodeEnum.SYS_EXEC_ERROR_B0001, ProjectConstants.APP_ID, "", message);
    }

    /**
     * TOKEN认证失败异常统一处理
     */
    @ExceptionHandler(TokenInvalidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean<String> handleAuthFailedException(TokenInvalidException exception) {
        return ResponseBean.failed(exception.getMessage(), BaseErrorCodeEnum.USER_AUTH_ACCESS_ERROR_A0300, ProjectConstants.APP_ID, "", exception.getMessage());
    }

    /**
     * 绑定参数异常统一处理
     */
    @ExceptionHandler({ConstraintViolationException.class, IllegalArgumentException.class,  HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, MissingPathVariableException.class, ServletRequestBindingException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean<String> handleBindException(Exception exception) {
        if (exception instanceof MissingServletRequestParameterException) {
            // 用户请求参数错误, 二级宏观错误码
            return ResponseBean.failed(BaseErrorCodeEnum.REQUEST_PARAM_REQUIRED_IS_EMPTY_A0410, ProjectConstants.APP_ID, "", exception.toString());
        }

        if (exception instanceof MissingPathVariableException) {
            // 用户请求参数错误, 二级宏观错误码
            return ResponseBean.failed(BaseErrorCodeEnum.REQUEST_PARAM_ERROR_A0400, ProjectConstants.APP_ID, "", exception.toString());
        }

        if (exception instanceof HttpMessageNotReadableException) {
            // 用户请求参数错误, 二级宏观错误码
            return ResponseBean.failed(BaseErrorCodeEnum.REQUEST_PARAM_ERROR_A0400, ProjectConstants.APP_ID, "", exception.toString());
        }

        // 用户请求参数错误, 二级宏观错误码
        return ResponseBean.failed(BaseErrorCodeEnum.REQUEST_PARAM_ERROR_A0400, ProjectConstants.APP_ID, "", exception.toString());
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean<String> handleBindException(BindException e) throws BindException {
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        FieldError fieldError = e.getFieldError();
        StringBuilder sb = new StringBuilder();
        sb.append(fieldError.getField()).append("=[").append(fieldError.getRejectedValue()).append("]")
                .append(fieldError.getDefaultMessage());
        // 生成返回结果
        return ResponseBean.failed(BaseErrorCodeEnum.REQUEST_PARAM_ERROR_A0400, ProjectConstants.APP_ID, "", sb.toString());
    }

    /**
     * 验证异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean<String> handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) throws MethodArgumentNotValidException {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("=[").append(fieldError.getDefaultMessage()).append("]")
                    .append(" ");
        }
        logger.info("MethodArgumentNotValidException",e);
        return ResponseBean.failed(BaseErrorCodeEnum.REQUEST_PARAM_ERROR_A0400, ProjectConstants.APP_ID, "", sb.toString());
    }

}
