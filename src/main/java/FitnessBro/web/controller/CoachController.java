package FitnessBro.web.controller;


import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.CoachConverter;
import FitnessBro.converter.ReviewConverter;
import FitnessBro.domain.Coach;
import FitnessBro.domain.Review;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.service.RegisterService.RegisterService;
import FitnessBro.service.ReviewService.ReviewService;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import FitnessBro.web.dto.Coach.CoachResponseDTO;
import FitnessBro.web.dto.ReviewResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
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
    private final RegisterService registerService;

    @GetMapping("/{coachId}/info")
    @Operation(summary = "코치 상세정보 API", description = "코치 id를 받아 코치 상세정보 전달")
    public ApiResponse<CoachResponseDTO.CoachProfileDTO> getCoachInfo(@PathVariable(value = "coachId") Long coachId, Errors errors) {

        return ApiResponse.onSuccess(CoachConverter.toCoachProfileDTO(coachService.getCoachById(coachId)));
    }


    //헬스장 id를 받지 않고 그냥 다 넘겨 줄 때
    @GetMapping("/search")
    @Operation(summary = "코치 리스트 API", description = "코치 리스트 전달")
    public ResponseEntity<ApiResponse<List<CoachResponseDTO.CoachDTO>>> getCoachList(){

        List<Coach> coachList = coachService.getCoachList();
        ApiResponse<List<CoachResponseDTO.CoachDTO>> apiResponse = ApiResponse.onSuccess(CoachConverter.toCoachListDTO(coachList));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{coachId}/reviews")
    @Operation(summary = "동네형이 받은 리뷰들을 조회 하는 API")
    public ApiResponse<List<ReviewResponseDTO.ReviewByCoachDTO>> getReviews(@PathVariable(value = "coachId") Long coachId){
        List<Review> reviews =  reviewService.getByCoachId(coachId);
        return ApiResponse.onSuccess(ReviewConverter.toReviewByCoachDTO(reviews));
    }

    @GetMapping("/{coachId}")
    @Operation(summary = "코치 마이페이지")
    public ApiResponse<CoachResponseDTO.CoachMyPageDTO> getCoachMyPage(@PathVariable(value = "coachId") Long coachId){
        return ApiResponse.onSuccess(CoachConverter.toCoachMyPageDTO(coachService.getCoachById(coachId), registerService.getMatchNumCoach(coachId),reviewService.getReviewNumCoach(coachId)));
    }

    @PatchMapping("/{coachId}")
    @Operation(summary = "코치 정보 수정")
    public ApiResponse<CoachResponseDTO.CoachUpdateResponseDTO> patchCoachUpdate(@PathVariable(value = "coachId") Long coachId, @RequestBody CoachRequestDTO.CoachUpdateRequestDTO coachUpdateRequestDTO){
        Coach coach = coachService.updateCoach(coachId,coachUpdateRequestDTO);
        return ApiResponse.onSuccess(CoachConverter.toCoachUpdateDTO(coach));
    }
}
