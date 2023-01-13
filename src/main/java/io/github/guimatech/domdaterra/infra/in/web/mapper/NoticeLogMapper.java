package io.github.guimatech.domdaterra.infra.in.web.mapper;

import io.github.guimatech.domdaterra.domain.NoticeLog;
import io.github.guimatech.domdaterra.infra.in.web.dto.NoticeLogRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface NoticeLogMapper {

    NoticeLogMapper INSTANCE = Mappers.getMapper(NoticeLogMapper.class);

    @Mapping(source = "request.startDatePeriod", target = "startDatePeriod", qualifiedByName = "stringToDate")
    @Mapping(source = "request.endDatePeriod", target = "endDatePeriod", qualifiedByName = "stringToDate")
    NoticeLog RequestToDomain(NoticeLogRequest request);

    @Named("stringToDate")
    static LocalDate stringToDate(String value) {
        return LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
    }
}
