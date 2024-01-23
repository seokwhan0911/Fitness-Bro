package FitnessBro.web.controller;


import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.CoachConverter;
import FitnessBro.converter.ReviewConverter;
import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.review.Entity.Review;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.service.ReviewService.ReviewService;
import FitnessBro.web.dto.CoachResponseDTO;
import FitnessBro.web.dto.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coaches")
@RequiredArgsConstructor
public class CoachController {
    private final CoachService coachService;
    private final ReviewService reviewService;


    @GetMapping("/{coachId}/info")
    public ApiResponse<CoachResponseDTO.CoachProfileDTO> getCoachInfo(@PathVariable(value = "coachId") Long coachId){
        return ApiResponse.onSuccess(CoachConverter.toCoachProfileDTO(coachService.getCoachById(coachId)));
    }


    @GetMapping("/{coachId}/reviews")
    public ApiResponse<List<ReviewResponseDTO.ReviewByCoachDTO>> getReviews(@PathVariable(value = "coachId") Long coachId){
        List<Review> reviews =  reviewService.getByCoachId(coachId);
        return ApiResponse.onSuccess(ReviewConverter.toReviewByCoachDTO(reviewService.getByCoachId(coachId)));
    }


}
