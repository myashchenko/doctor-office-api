package ua.kpi.mapper;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Mykola Yashchenko
 */
public class LocalDateMapper extends BidirectionalConverter<String, LocalDate> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public LocalDate convertTo(String s, Type<LocalDate> type) {
        return LocalDate.parse(s, formatter);
    }

    @Override
    public String convertFrom(LocalDate localDate, Type<String> type) {
        return localDate.toString();
    }
}
