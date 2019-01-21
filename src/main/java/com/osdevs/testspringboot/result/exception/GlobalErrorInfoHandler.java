package com.osdevs.testspringboot.result.exception;

import com.osdevs.testspringboot.result.ResultBody;
import com.osdevs.testspringboot.result.error.ErrorInfoInterface;
import com.osdevs.testspringboot.result.error.GlobalErrorInfoEnum;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 统一错误码异常处理
 */
@RestControllerAdvice
public class GlobalErrorInfoHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalErrorInfoHandler.class);

//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e){
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("error");
//        return mav;
//    }
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ResultBody errorHandlerOverJson(HttpServletRequest request,
                                           Exception ex) {
        ex.printStackTrace();
        ResultBody result;
        if (ex instanceof GlobalErrorInfoException){
            ErrorInfoInterface errorInfo = ((GlobalErrorInfoException)ex).getErrorInfo();
            if (errorInfo!=null){
                logger.error(request.getContextPath()+""+errorInfo.getMessage());
                result = new ResultBody(errorInfo);
            }else{
                logger.error(request.getContextPath()+""+ex.getMessage());
                result = new ResultBody(GlobalErrorInfoEnum.NOT_FOUND);
            }
        } else if (ex instanceof HttpMessageNotReadableException||ex instanceof MethodArgumentNotValidException){
            logger.error(request.getContextPath()+""+ex.getMessage());
            result = new ResultBody(GlobalErrorInfoEnum.PARAM_ERROR);
        }else {
            logger.error(request.getContextPath()+""+ex.getMessage());
            result = new ResultBody(GlobalErrorInfoEnum.NOT_FOUND);
        }


        return result;
    }
}
