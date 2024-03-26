package com.mc.dto;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.util.Locale;

@Convert
public class OrderStatusConvert implements AttributeConverter<OrderStatusEnum, String> {

    @Override
    public String convertToDatabaseColumn(OrderStatusEnum orderStatusEnum) {
        return orderStatusEnum.getCode();
    }

    @Override
    public OrderStatusEnum convertToEntityAttribute(String code) {
        for (OrderStatusEnum orderStatus : OrderStatusEnum.values()) {
            if(orderStatus.getCode().equals(code)) {
                return orderStatus;
            }
        }
        throw new RuntimeException("Unknown database value: " + code);
    }
}
