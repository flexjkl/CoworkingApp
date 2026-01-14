package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.input.UserCreationDTO;
import dev.vorstu.coworkingapp.dto.output.PersonOutputDTO;
import dev.vorstu.coworkingapp.enums.Role;
import dev.vorstu.coworkingapp.services.AdminService;
import dev.vorstu.coworkingapp.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Администратор",
        description = "Предоставляет функционал для администраторов"
)
@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;



    @DeleteMapping("/reviews/{id}")
    @SecurityRequirement(name = "JWT")
    public Long deleteReview(@PathVariable Long id) {
        return adminService.deleteReview(id);
    }



    @DeleteMapping("/comments/{id}")
    @SecurityRequirement(name = "JWT")
    public Long deleteComment(@PathVariable Long id) {
        return adminService.deleteComment(id);
    }



    @DeleteMapping("/spaces/{id}")
    @SecurityRequirement(name = "JWT")
    public Long deleteSpace(@PathVariable Long id) {
        return adminService.deleteSpace(id);
    }



    @DeleteMapping("/coworkingplaces/{id}")
    @SecurityRequirement(name = "JWT")
    public Long deleteCoworkingPlace(@PathVariable Long id) {
        return adminService.deleteCoworkingPlace(id);
    }



    @GetMapping("/persons")
    @SecurityRequirement(name = "JWT")
    public Page<PersonOutputDTO> getUsers(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String secondname,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String email,
            @PageableDefault Pageable pageable
    ) {
        return adminService.getPersons(
                id,
                username,
                role,
                firstname,
                secondname,
                lastname,
                email,
                pageable
        );
    }

    @PostMapping("/persons")
    @SecurityRequirement(name = "JWT")
    public UserCreationDTO createUser(UserCreationDTO userCreationDTO) {
        userService.createUser(userCreationDTO);
        return userCreationDTO;
    }

    @DeleteMapping("/persons/{id}")
    @SecurityRequirement(name = "JWT")
    public Long deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
