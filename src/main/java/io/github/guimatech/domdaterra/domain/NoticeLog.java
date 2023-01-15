package io.github.guimatech.domdaterra.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
@Table
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class NoticeLog extends PersistentObject {

    private String description;
    private LocalDate startDatePeriod;
    private LocalDate endDatePeriod;

    @JsonIgnore
    public String getPeriod() {
        if (Objects.isNull(this.startDatePeriod) || Objects.isNull(this.endDatePeriod))
            return "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("De %s até %s", formatter.format(this.startDatePeriod), formatter.format(this.endDatePeriod));
    }
}
