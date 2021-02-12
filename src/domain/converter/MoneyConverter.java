package domain.converter;

import javax.persistence.AttributeConverter;

import domain.Money;


import java.math.BigDecimal;

public class MoneyConverter implements AttributeConverter<Money, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(Money entity) {
        if(entity == null) {
            return null;
        }
        return entity.getAmount();
    }

    @Override
    public Money convertToEntityAttribute(BigDecimal value) {
        if(value != null) {
            return new Money(value);
        }
        return new Money();
    }
}