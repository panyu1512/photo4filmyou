package domain.converter;

import javax.persistence.AttributeConverter;

import domain.Location;

public class LocationConverter implements AttributeConverter<Location, String> {

    @Override
    public String convertToDatabaseColumn(Location entity) {
        return entity.getValue();
    }

    @Override
    public Location convertToEntityAttribute(String value) {
        return new Location(value);
    }
}
