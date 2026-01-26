package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.output.BookingOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimBookingOutputDTO;
import dev.vorstu.coworkingapp.entities.jpa.places.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CoworkingPlaceMapper.class)
public interface BookingMapper {

    @Mapping(source = "client.username", target = "username")
    @Mapping(source = "pricePlan.id", target = "pricePlanId")
    @Mapping(source = "chat.id", target = "chatId")
    BookingOutputDTO toDTO(Booking booking);

    List<BookingOutputDTO> listToDTO(List<Booking> bookings);

    SlimBookingOutputDTO toSlimDTO(Booking booking);

    List<SlimBookingOutputDTO> listToSlimDTO(List<Booking> bookings);
}
