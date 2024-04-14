package com.atguigu.cloud.exception;


import com.atguigu.cloud.entities.enums.ReturnCodeEnum;
import com.atguigu.cloud.respon.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author aiqiku
 * @create 2024/4/10 12:02
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e){
        System.out.println("GlobalExceptionHandler is handing");
        log.error("全局异常信息:{}",e.getMessage());
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
    }
}
