package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.communication.Message;
import dev.vorstu.coworkingapp.entities.places.Space;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository  extends JpaRepository<Message, Long> {
}
