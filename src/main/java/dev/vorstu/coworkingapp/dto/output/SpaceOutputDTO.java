package dev.vorstu.coworkingapp.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SpaceOutputDTO {

    private Long id;

    private PersonOutputDTO owner;

    private String title;

    private BigDecimal rating;

}
