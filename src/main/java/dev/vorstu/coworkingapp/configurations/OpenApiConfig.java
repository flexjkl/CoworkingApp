package dev.vorstu.coworkingapp.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Coworking Space Booking API",
                description = "API системы бронирования коворкингов",
                version = "0.0.1",
                contact = @Contact(
                        name = "Sergeev Kirill",
                        email = "leenshp@gmail.com"
                )
        )
)
public class OpenApiConfig {
}
