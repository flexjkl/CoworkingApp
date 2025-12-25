package dev.vorstu.coworkingapp.entities.utils;

import dev.vorstu.coworkingapp.enums.PricePlanType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class PricePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private PricePlanType type;

}
