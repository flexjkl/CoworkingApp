package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.output.CommentOutputDTO;
import dev.vorstu.coworkingapp.dto.output.CoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;




    @GetMapping("/reviews")
    public Page<ReviewOutputDTO> getAllReviews(@PageableDefault Pageable pageable) {
        return adminService.getAllReviews(pageable);
    }

    @GetMapping("/reviews/{id}")
    public ReviewOutputDTO getReview(@PathVariable Long id) {
        return adminService.getReview(id);
    }

    @DeleteMapping("/reviews/{id}")
    public Long deleteReview(@PathVariable Long id) {
        return adminService.deleteReview(id);
    }



    @GetMapping("/comments")
    public Page<CommentOutputDTO> getAllComments(@PageableDefault Pageable pageable) {
        return adminService.getAllComments(pageable);
    }

    @GetMapping("/comments/{id}")
    public CommentOutputDTO getComment(@PathVariable Long id) {
        return adminService.getComment(id);
    }

    @DeleteMapping("/comments/{id}")
    public Long deleteComment(@PathVariable Long id) {
        return adminService.deleteComment(id);
    }



    @GetMapping("/spaces")
    public Page<SpaceOutputDTO> getAllSpaces(@PageableDefault Pageable pageable) {
        return adminService.getAllSpaces(pageable);
    }

    @GetMapping("/spaces/{id}")
    public SpaceOutputDTO getSpace(@PathVariable Long id) {
        return adminService.getSpace(id);
    }

    @DeleteMapping("/spaces/{id}")
    public Long deleteSpace(@PathVariable Long id) {
        return adminService.deleteSpace(id);
    }



    @GetMapping("/coworkingplaces")
    public Page<CoworkingPlaceOutputDTO> getAllCoworkingPlaces(@PageableDefault Pageable pageable) {
        return adminService.getAllCoworkingPlaces(pageable);
    }

    @GetMapping("/coworkingplaces/{id}")
    public CoworkingPlaceOutputDTO getCoworkingPlace(@PathVariable Long id) {
        return adminService.getCoworkingPlace(id);
    }

    @DeleteMapping("/coworkingplaces/{id}")
    public Long deleteCoworkingPlace(@PathVariable Long id) {
        return adminService.deleteCoworkingPlace(id);
    }




}
