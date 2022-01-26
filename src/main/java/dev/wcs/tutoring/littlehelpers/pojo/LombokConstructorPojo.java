package dev.wcs.tutoring.littlehelpers.pojo;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@AllArgsConstructor
public class LombokConstructorPojo {

    private @NonNull String id;
    private LocalDate created;
    private BigDecimal value;

}
