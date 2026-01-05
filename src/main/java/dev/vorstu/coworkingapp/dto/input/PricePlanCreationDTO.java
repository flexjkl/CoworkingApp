package dev.vorstu.coworkingapp.dto.input;

import dev.vorstu.coworkingapp.enums.PricePlanType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PricePlanCreationDTO {

    private String title;

    private BigDecimal price;

    private PricePlanType type;

}
