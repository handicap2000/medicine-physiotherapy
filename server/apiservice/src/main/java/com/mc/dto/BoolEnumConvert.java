package com.mc.dto;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class BoolEnumConvert implements AttributeConverter<BoolEnum,String> {
    @Override
    public String convertToDatabaseColumn(BoolEnum boolEnum) {
        if (boolEnum == null) {
            return null;
        }
        return boolEnum.getStatus();
    }

    @Override
    public BoolEnum convertToEntityAttribute(String status) {
        if (status == null) {
            return null;
        }
        for (BoolEnum bitStatus : BoolEnum.values()) {
            if (bitStatus.getStatus().equals(status)) {
                return bitStatus;
            }
        }
        throw new RuntimeException("Unknown database value: " + status);
    }
}
