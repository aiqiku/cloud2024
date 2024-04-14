package com.atguigu.cloud.entities.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author aiqiku
 * @create 2024/4/10 11:09
 */
@Getter
public enum SeasonEnum {
    //举值 -> 构造 -> 遍历
    SPRING("春天",1),
    SUMMER("夏天",2),

    AUTUMN("秋天",3),

    WINTER("冬天",4);

    private final String name;

    private final Integer code;

    SeasonEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public static SeasonEnum getSeasonEnum(Integer code){
       return Arrays.stream(SeasonEnum.values()).filter(x -> x.getCode().equals(code)).findFirst().orElse(null);
    }


}
