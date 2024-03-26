package com.mc.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum BoolEnum {
    /**
     * 假
     */
    FALSE("false"),

    /**
     * 真
     */
    TRUE("true");

    private String status;

    BoolEnum(String status) {
        this.status = status;
    }
    @JsonValue
    public String getStatus() {
        return status;
    }
}
