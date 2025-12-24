package dev.vorstu.coworkingapp.entities.users;

import dev.vorstu.coworkingapp.entities.communication.Chat;
import dev.vorstu.coworkingapp.entities.places.Space;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Owner extends Person{

    @OneToMany(mappedBy = "owner")
    private List<Space> spaces = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Chat> chats = new ArrayList<>();

}
