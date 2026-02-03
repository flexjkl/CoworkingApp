package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.jpa.communication.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
