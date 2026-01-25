package dev.vorstu.coworkingapp.dto.output;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SpaceOutputDTO {

    @NotNull
    private Long id;

    @NotNull
    private PersonOutputDTO owner;

    @NotBlank
    private String title;

    @NotNull
    private BigDecimal rating;

}
