package dev.vorstu.coworkingapp.dto.output.slims;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SlimSpaceOutputDTO {
    private String id;

    private String title;

    private BigDecimal rating;
}
