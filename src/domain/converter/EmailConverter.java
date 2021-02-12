package domain.converter;

import javax.persistence.AttributeConverter;

import domain.Email;

public class EmailConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email entity) {
        return entity.getValue();
    }

    @Override
    public Email convertToEntityAttribute(String value) {
        return new Email(value);
    }
}
