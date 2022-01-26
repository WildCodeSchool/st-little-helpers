package dev.wcs.tutoring.littlehelpers.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@Builder
public class PokeCity {

    private @NonNull String name;
    private @NonNull Integer amountItems;

}
