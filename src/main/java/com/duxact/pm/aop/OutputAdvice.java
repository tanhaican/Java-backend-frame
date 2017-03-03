package com.duxact.pm.aop;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.duxact.pm.constant.Constants;
import com.duxact.pm.output.BaseResponseVo;

@Component
@Aspect
public class OutputAdvice {
	private static final String RESPONSE_CHARSET = "UTF-8";
	/** 
     * Pointcut  
     * 定义Pointcut，Pointcut名称为formatOutput,必须无参，无返回值 
     * 只是一个标识，并不进行调用 
     */  
    @Pointcut("execution(* com.duxact.pm.biz.*.controller.*.*(..))")  
    private void formatJsonOutput(){}

    @AfterReturning(returning="ret", pointcut="formatJsonOutput()")
    public void output(Object ret) {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//获取request
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();//获取response

    	if (!checkHasFlag(request) || !checkReturnValueIsPb(ret)) {
    		BaseResponseVo<Object> baseVo = new BaseResponseVo<Object>();
    		baseVo.setRetCode(Constants.ResponseCode.FAILED.getCode());
    		baseVo.setRetMsg("异常请求");
    		ret = baseVo;
    	}
    	String json = JSONObject.toJSONString(ret);//转换json
    	
    	ServletOutputStream out = null;
    	try {
    		response.setCharacterEncoding(RESPONSE_CHARSET);
    		response.setContentType("text/json;charset=" + RESPONSE_CHARSET);
    		out = response.getOutputStream();
    		out.write(json.getBytes(RESPONSE_CHARSET));
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		if (null != out) {
    			try {
    				out.flush();
    				out.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
    
    private boolean checkReturnValueIsPb(Object returnValue) {
        if (returnValue instanceof BaseResponseVo) {//检查是否是pb
            return true;
        } else {
            return false;
        }
    }

    private boolean checkHasFlag(HttpServletRequest request) {
        if (null == request) return false;
        String contentType = request.getContentType();
        String accept = request.getHeader("Accept");
        //检查accept和content-type
        if (StringUtils.isNotBlank(contentType) && (contentType.contains("application/json") || contentType.contains("application/x-www-form-urlencoded")) 
        		|| StringUtils.isNotBlank(accept) && accept.contains("application/json") ) {
            return true;
        } else {
            return false;
        }
    }
}
