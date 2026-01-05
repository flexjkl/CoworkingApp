package dev.vorstu.coworkingapp.dto.output;

import dev.vorstu.coworkingapp.enums.PricePlanType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PricePlanOutputDTO {

    private Long id;

    private String title;

    private BigDecimal price;

    private PricePlanType type;

    private Long spaceId;

}
