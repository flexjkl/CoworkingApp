package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.CommentCreationDTO;
import dev.vorstu.coworkingapp.dto.input.PricePlanCreationDTO;
import dev.vorstu.coworkingapp.dto.input.SpaceCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.CommentUpdateDTO;
import dev.vorstu.coworkingapp.dto.input.update.CoworkingPlaceUpdateDTO;
import dev.vorstu.coworkingapp.dto.input.update.PricePlanUpdateDTO;
import dev.vorstu.coworkingapp.dto.input.update.SpaceUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.CommentOutputDTO;
import dev.vorstu.coworkingapp.dto.output.PricePlanOutputDTO;
import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimCoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.exceptions.accessdenied.AccessDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final SpaceService spaceService;
    private final CoworkingPlaceService coworkingPlaceService;
    private final CommentService commentService;
    private final PricePlanService pricePlanService;



    public Page<SlimSpaceOutputDTO> getMySpaces(Long ownerId,
                                                String titleMatcher,
                                                Pageable pageable
    ) {
        return spaceService.getSpaces(ownerId, titleMatcher, pageable);
    }

    public SpaceOutputDTO getMySpace(Long spaceId, Long ownerId) {

        if(!spaceService.isSpaceOwnedByOwner(ownerId, spaceId)) {
            throw new AccessDeniedException();
        }

        return spaceService.getSpaceById(spaceId);
    }

    public SpaceCreationDTO createSpace(Long ownerId, SpaceCreationDTO spaceCreationDTO) {
        return spaceService.createSpace(ownerId, spaceCreationDTO);
    }

    public SpaceUpdateDTO updateSpace(Long spaceId, Long ownerId, SpaceUpdateDTO spaceUpdateDTO) {

        if(!spaceService.isSpaceOwnedByOwner(ownerId, spaceId)) {
            throw new AccessDeniedException();
        }

        return spaceService.updateSpace(spaceId, spaceUpdateDTO);
    }

    public Long deleteSpace(Long myId, Long spaceId) {
        if(!spaceService.isSpaceOwnedByOwner(myId, spaceId)) {
            throw new AccessDeniedException();
        }

        return spaceService.deleteSpace(spaceId);
    }

    public Page<CommentOutputDTO> getMyComments(Long id,
                                                Long authorId,
                                                Long commentedSpaceId,
                                                Long parentId,
                                                Pageable pageable
    ) {
        return commentService.getComments(id, authorId, commentedSpaceId, parentId, pageable);
    }

    public CommentCreationDTO createComment(Long authorId, CommentCreationDTO commentCreationDTO) {
        return commentService.createComment(authorId, commentCreationDTO);
    }

    public CommentUpdateDTO updateComment(Long myId, Long commentId, CommentUpdateDTO commentUpdateDTO) {

        if(!commentService.isCommentOwnedByUser(commentId, myId)) {
            throw new AccessDeniedException();
        }

        return commentService.updateComment(commentId, commentUpdateDTO);
    }

    public Long deleteComment(Long myId, Long commentId) {

        if(!commentService.isCommentOwnedByUser(commentId, myId)) {
            throw new AccessDeniedException();
        }

        return commentService.deleteComment(commentId);
    }



    public Page<PricePlanOutputDTO> getPricePlansBySpaceId(Long myId, Long spaceId, Pageable pageable) {
        if(!spaceService.isSpaceOwnedByOwner(myId, spaceId)) {
            throw new AccessDeniedException();
        }

        return pricePlanService.getPricePlansBySpaceId(spaceId, pageable);
    }

    public PricePlanCreationDTO createPricePlan(Long spaceId, PricePlanCreationDTO pricePlanCreationDTO) {
        return pricePlanService.createPricePlan(spaceId, pricePlanCreationDTO);
    }

    public PricePlanUpdateDTO updatePricePlan(Long ownerId, Long spaceId, Long pricePlanId, PricePlanUpdateDTO pricePlanUpdateDTO) {
        if(!spaceService.isSpaceOwnedByOwner(ownerId, spaceId)) {
            throw new AccessDeniedException();
        }

        return pricePlanService.updatePricePlan(pricePlanId, pricePlanUpdateDTO);
    }

    public Long deletePricePlan(Long ownerId, Long spaceId, Long pricePlanId) {
        if(!spaceService.isSpaceOwnedByOwner(ownerId, spaceId)) {
            throw new AccessDeniedException();
        }

        return pricePlanService.deletePricePlan(pricePlanId);
    }
}
