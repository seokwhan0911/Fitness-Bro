package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.UserConverter;
import FitnessBro.domain.user.Entity.Member;
import FitnessBro.service.ReviewService.ReviewService;
import FitnessBro.service.UserService.UserCommandService;
import FitnessBro.web.dto.ReviewRequestDTO;
import FitnessBro.web.dto.ReviewResponseDTO;
import FitnessBro.web.dto.UserRequestDTO;
import FitnessBro.web.dto.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserCommandService userCommandService;
    private final ReviewService reviewService;

    @PostMapping("/")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDTO request){
        Member user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    @GetMapping("/{userId}/reviews")
    public ApiResponse<List<ReviewResponseDTO.ReviewByUserDTO>> getReviewsByUser(@PathVariable(value = "userId") Long userId ){
        return ApiResponse.onSuccess(reviewService.getReviews(userId));
    }

    @PostMapping("/{userId}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewByUserDTO> createReviews(
            @Valid @RequestBody ReviewRequestDTO.CreateReviewDTO createReviewDTO, @PathVariable(value = "userId") Long userId ){
        return ApiResponse.onSuccess(reviewService.createReview(createReviewDTO, userId));
    }
}
