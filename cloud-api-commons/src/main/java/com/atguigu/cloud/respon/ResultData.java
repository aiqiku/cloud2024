package com.atguigu.cloud.respon;


import com.atguigu.cloud.entities.enums.ReturnCodeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author aiqiku
 * @create 2024/4/10 11:24
 */
@Data
@Accessors(chain = true) //允许链式编程
public class ResultData<T>{
    private String code;

    private String message;

    private T Data;

    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data){
        ResultData resultData = new ResultData();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }
    public static <T> ResultData<T> success(){
        ResultData resultData = new ResultData();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        return resultData;
    }

    public static <T> ResultData<T> fail(String code,String message){
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);
        return resultData;
    }

}
