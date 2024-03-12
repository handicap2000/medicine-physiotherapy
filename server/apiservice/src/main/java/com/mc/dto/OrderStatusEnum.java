package com.mc.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum OrderStatusEnum {
    /**
     * 待使用
     */
    UNUSED("UNUSED", "待使用"),

    /**
     * 已使用
     */
    USED("USED", "已使用"),

    /**
     * 取消
     */
    CANCEL("CANCEL", "取消");

    private String code;
    private String status;

    OrderStatusEnum(String code, String status) {
        this.code = code;
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
