package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.output.*;
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



    @GetMapping("/reviews")
    @SecurityRequirement(name = "JWT")
    public Page<ReviewOutputDTO> getAllReviews(@PageableDefault Pageable pageable) {
        return adminService.getAllReviews(pageable);
    }

    @GetMapping("/reviews/{id}")
    @SecurityRequirement(name = "JWT")
    public ReviewOutputDTO getReview(@PathVariable Long id) {
        return adminService.getReview(id);
    }

    @DeleteMapping("/reviews/{id}")
    @SecurityRequirement(name = "JWT")
    public Long deleteReview(@PathVariable Long id) {
        return adminService.deleteReview(id);
    }



    @GetMapping("/comments")
    @SecurityRequirement(name = "JWT")
    public Page<CommentOutputDTO> getAllComments(@PageableDefault Pageable pageable) {
        return adminService.getAllComments(pageable);
    }

    @GetMapping("/comments/{id}")
    @SecurityRequirement(name = "JWT")
    public CommentOutputDTO getComment(@PathVariable Long id) {
        return adminService.getComment(id);
    }

    @DeleteMapping("/comments/{id}")
    @SecurityRequirement(name = "JWT")
    public Long deleteComment(@PathVariable Long id) {
        return adminService.deleteComment(id);
    }



    @GetMapping("/spaces")
    @SecurityRequirement(name = "JWT")
    public Page<SpaceOutputDTO> getAllSpaces(@PageableDefault Pageable pageable) {
        return adminService.getAllSpaces(pageable);
    }

    @GetMapping("/spaces/{id}")
    @SecurityRequirement(name = "JWT")
    public SpaceOutputDTO getSpace(@PathVariable Long id) {
        return adminService.getSpace(id);
    }

    @DeleteMapping("/spaces/{id}")
    @SecurityRequirement(name = "JWT")
    public Long deleteSpace(@PathVariable Long id) {
        return adminService.deleteSpace(id);
    }



    @GetMapping("/coworkingplaces")
    @SecurityRequirement(name = "JWT")
    public Page<CoworkingPlaceOutputDTO> getAllCoworkingPlaces(@PageableDefault Pageable pageable) {
        return adminService.getAllCoworkingPlaces(pageable);
    }

    @GetMapping("/coworkingplaces/{id}")
    @SecurityRequirement(name = "JWT")
    public CoworkingPlaceOutputDTO getCoworkingPlace(@PathVariable Long id) {
        return adminService.getCoworkingPlace(id);
    }

    @DeleteMapping("/coworkingplaces/{id}")
    @SecurityRequirement(name = "JWT")
    public Long deleteCoworkingPlace(@PathVariable Long id) {
        return adminService.deleteCoworkingPlace(id);
    }



    @GetMapping("/clients")
    @SecurityRequirement(name = "JWT")
    public Page<PersonOutputDTO> getAllClients(@PageableDefault Pageable pageable) {
        return adminService.getAllClients(pageable);
    }

    @GetMapping("/clients/{id}")
    @SecurityRequirement(name = "JWT")
    public PersonOutputDTO getClient(@PathVariable Long id) {
        return adminService.getClient(id);
    }

    @DeleteMapping("/clients/{id}")
    @SecurityRequirement(name = "JWT")
    public Long deleteClient(@PathVariable Long id) {
        return adminService.deleteClient(id);
    }



    @GetMapping("/owners")
    public Page<PersonOutputDTO> getAllOwners(@PageableDefault Pageable pageable) {
        return adminService.getAllOwners(pageable);
    }

    @GetMapping("/owners/{id}")
    public PersonOutputDTO getOwner(@PathVariable Long id) {
        return adminService.getOwner(id);
    }

    @DeleteMapping("/owners/{id}")
    public Long deleteOwner(@PathVariable Long id) {
        return adminService.deleteOwner(id);
    }
}
