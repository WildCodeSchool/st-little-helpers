package dev.wcs.tutoring.littlehelpers.pojo;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
public class City {

    private @NonNull String name;
    private @NonNull Integer population;
    private LocalDate founded;
    private boolean capital;

}
