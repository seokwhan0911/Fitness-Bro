package FitnessBro.web.controller;


import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.apiPayload.code.status.ErrorStatus;
import FitnessBro.converter.CoachConverter;
import FitnessBro.converter.ReviewConverter;
import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.review.Entity.Review;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.service.ReviewService.ReviewService;
import FitnessBro.web.dto.CoachResponseDTO;
import FitnessBro.web.dto.ReviewResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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
    @Operation(summary = "코치 마이페이지 API", description = "코치 id를 받아 코치 마이페이지 전달")
    public ApiResponse<CoachResponseDTO.CoachProfileDTO> getCoachInfo(@PathVariable(value = "coachId") Long coachId, Errors errors){

        return ApiResponse.onSuccess(CoachConverter.toCoachProfileDTO(coachService.getCoachById(coachId)));
    }

    // 헬스장 id를 받는다능 가정하에 미완성
//    @GetMapping("/{gymId}/search")
//    @Operation(summary = "코치 리스트 API", description = "헬스장 id를 받아 코치 리스트 전달")
//    public ResponseEntity<ApiResponse<List<CoachResponseDTO.CoachDTO>>> getCoachList(@PathVariable(value = "gymId")Long gymId, Errors errors){
//        List<CoachResponseDTO.CoachDTO> coachList = coachService.getCoachList(gymId);
//        return null;
//    }

    //헬스장 id를 받지 않고 그냥 다 넘겨 줄 때
    @GetMapping("/search")
    @Operation(summary = "코치 리스트 API", description = "코치 리스트 전달")
    public ResponseEntity<ApiResponse<List<CoachResponseDTO.CoachDTO>>> getCoachList(){

        List<Coach> coachList = coachService.getCoachList();
        ApiResponse<List<CoachResponseDTO.CoachDTO>> apiResponse = ApiResponse.onSuccess(CoachConverter.toCoachListDTO(coachList));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/{coachId}/reviews")
    public ApiResponse<List<ReviewResponseDTO.ReviewByCoachDTO>> getReviews(@PathVariable(value = "coachId") Long coachId){
        List<Review> reviews =  reviewService.getByCoachId(coachId);
        return ApiResponse.onSuccess(ReviewConverter.toReviewByCoachDTO(reviewService.getByCoachId(coachId)));
    }

}
