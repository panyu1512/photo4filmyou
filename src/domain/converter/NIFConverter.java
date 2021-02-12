package domain.converter;

import javax.persistence.AttributeConverter;

import domain.NIF;

public class NIFConverter implements AttributeConverter<NIF, String> {

    @Override
    public String convertToDatabaseColumn(NIF entity) {
        return entity.getValue();
    }

    @Override
    public NIF convertToEntityAttribute(String value) {
        return new NIF(value);
    }
}
