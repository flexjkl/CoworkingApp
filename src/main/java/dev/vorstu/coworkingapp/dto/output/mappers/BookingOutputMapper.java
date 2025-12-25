package dev.vorstu.coworkingapp.dto.output.mappers;

import dev.vorstu.coworkingapp.dto.output.BookingOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimBookingOutputDTO;
import dev.vorstu.coworkingapp.entities.places.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CoworkingPlaceOutputMapper.class)
public interface BookingOutputMapper {

    @Mapping(expression = """
            java(booking.getClient().getFirstname().charAt(0) +
            ". " + booking.getClient().getSecondname().charAt(0) +
            ". " + booking.getClient().getLastname
            """, target = "initials"
    )
    @Mapping(source = "pricePlan.id", target = "pricePlanId")
    @Mapping(source = "chat.id", target = "chatId")
    BookingOutputDTO toDTO(Booking booking);

    List<BookingOutputDTO> listToDTO(List<Booking> bookings);

    SlimBookingOutputDTO toSlimDTO(Booking booking);

    List<SlimBookingOutputDTO> listToSlimDTO(List<Booking> bookings);
}
