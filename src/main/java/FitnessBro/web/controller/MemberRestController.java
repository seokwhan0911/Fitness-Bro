package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.MemberConverter;
import FitnessBro.domain.member.Entity.Member;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.service.ReviewService.ReviewService;
import FitnessBro.web.dto.MemberRequestDTO;
import FitnessBro.web.dto.MemberResponseDTO;
import FitnessBro.web.dto.ReviewRequestDTO;
import FitnessBro.web.dto.ReviewResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberRestController {


    private final MemberCommandService memberCommandService;
    private final ReviewService reviewService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request){

        Member user = memberCommandService.joinUser(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(user));
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
