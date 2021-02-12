package domain.converter;

import javax.persistence.AttributeConverter;

import domain.Password;

public class PasswordConverter implements AttributeConverter<Password, String> {

    @Override
    public String convertToDatabaseColumn(Password entity) {
        return entity.getValue();
    }

    @Override
    public Password convertToEntityAttribute(String value) {
        return new Password(value);
    }
}
