package com.dokito.letshelp.data.models;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements
        AttributeConverter<LocalDateTime, Timestamp> {

//    @Override
//    public Date convertToDatabaseColumn(LocalDate locDate) {
//        return (locDate == null ? null : Date.valueOf(locDate));
//    }
//
//    @Override
//    public LocalDate convertToEntityAttribute(Date sqlDate) {
//        return (sqlDate == null ? null : sqlDate.toLocalDate());
//    }

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return (localDateTime == null ? null : Timestamp.valueOf(localDateTime));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return (timestamp == null ? null : timestamp.toLocalDateTime());
    }
}
