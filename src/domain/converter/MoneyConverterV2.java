package domain.converter;

import javax.persistence.AttributeConverter;

import domain.MoneyV2;

import java.math.BigDecimal;

public class MoneyConverterV2 implements AttributeConverter<MoneyV2, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(MoneyV2 entity) {
        if(entity == null) {
            return null;
        }
        return entity.getAmount();
    }

    @Override
    public MoneyV2 convertToEntityAttribute(BigDecimal value) {
        if(value != null) {
            return new MoneyV2(value);
        }
        return new MoneyV2();
    }
}

