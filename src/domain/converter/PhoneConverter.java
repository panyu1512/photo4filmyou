package domain.converter;

import javax.persistence.AttributeConverter;

import domain.PhoneNumber;

public class PhoneConverter  implements AttributeConverter<PhoneNumber, String> {

    @Override
    public String convertToDatabaseColumn(PhoneNumber entity) {
        return entity.getValue();
    }

    @Override
    public PhoneNumber convertToEntityAttribute(String value) {
        return new PhoneNumber(value);
    }

}
