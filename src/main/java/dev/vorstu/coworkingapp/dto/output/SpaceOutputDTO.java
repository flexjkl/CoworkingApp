package dev.vorstu.coworkingapp.dto.output;

import dev.vorstu.coworkingapp.dto.output.slims.SlimCoworkingPlaceOutputDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//todo Пока подгрузка комментариев, мест, обзоров идёт отдельно
@Getter
@Setter
public class SpaceOutputDTO {

    private Long id;

    private PersonOutputDTO owner;

    private String title;

    private BigDecimal rating;

}
