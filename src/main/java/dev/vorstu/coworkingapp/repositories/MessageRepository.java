package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.jpa.communication.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository  extends JpaRepository<Message, Long> {
}
