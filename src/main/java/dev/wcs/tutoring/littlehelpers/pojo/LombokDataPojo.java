package dev.wcs.tutoring.littlehelpers.pojo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LombokDataPojo {

    private @Setter(AccessLevel.PUBLIC) String attribute;
    private LocalDate created;
    private BigDecimal value;

}
