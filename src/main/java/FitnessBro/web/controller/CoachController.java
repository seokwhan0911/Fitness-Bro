package FitnessBro.web.controller;


import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.CoachConverter;
import FitnessBro.converter.ReviewConverter;
import FitnessBro.domain.Coach;
import FitnessBro.domain.Review;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.service.ReviewService.ReviewService;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import FitnessBro.web.dto.Coach.CoachResponseDTO;
import FitnessBro.web.dto.Login.LoginRequestDTO;
import FitnessBro.web.dto.ReviewResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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

    @PutMapping("/{coachId}/sign-up")
    @Operation(summary = "동네형 회원가입 완료 후 첫 정보 입력 페이지")
    public ApiResponse<String> coachSignUp(@PathVariable(value = "coachId") Long coachId,
                                           @RequestBody @Valid CoachRequestDTO.CoachProfileRegisterDTO request){

        Optional<Coach> coach = coachService.insertInfo(coachId, request);

        return ApiResponse.onSuccess("Success");

    }


}
