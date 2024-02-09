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
import FitnessBro.web.dto.review.ReviewResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/coaches")
@RequiredArgsConstructor
public class CoachController {

    private final CoachService coachService;
    private final ReviewService reviewService;
    private final RegisterService registerService;

    @GetMapping("/{coachId}/info")
    @Operation(summary = "동네형 상세정보 API", description = "동네형 id(coachId)를 받아 동네형 상세정보 전달")
    public ApiResponse<CoachResponseDTO.CoachProfileDTO> getCoachInfo(@PathVariable(value = "coachId") Long coachId) {

        return ApiResponse.onSuccess(CoachConverter.toCoachProfileDTO(coachService.getCoachById(coachId)));
    }


    //헬스장 id를 받지 않고 그냥 다 넘겨 줄 때
    @GetMapping("/search")
    @Operation(summary = "동네형 리스트 API", description = "동네형 리스트 전달")
    public ResponseEntity<ApiResponse<List<CoachResponseDTO.CoachDTO>>> getCoachList() {

        List<Coach> coachList = coachService.getCoachList();
        ApiResponse<List<CoachResponseDTO.CoachDTO>> apiResponse = ApiResponse.onSuccess(CoachConverter.toCoachListDTO(coachList));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/{coachId}/reviews")
    @Operation(summary = "동네형이 받은 리뷰들을 조회 하는 API")
    public ApiResponse<List<ReviewResponseDTO.ReviewByCoachDTO>> getReviews(@PathVariable(value = "coachId") Long coachId) {

        try {
            List<Review> reviews = reviewService.getReviewsByCoachId(coachId);

            List<ReviewResponseDTO.ReviewByCoachDTO> reviewDTOList = ReviewConverter.toReviewByCoachDTO(reviews);

            return ApiResponse.onSuccess(reviewDTOList);

        } catch (Exception e) {
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @GetMapping("/reviews/{reviewId}")
    @Operation(summary = "동네형이 받은 리뷰 상세보기를 조회하는 API")
    public ApiResponse<ReviewResponseDTO.ReviewDetailDTO> getReviewDetails(@PathVariable(value = "reviewId") Long reviewId) {

        try {
            Review review = reviewService.getReviewById(reviewId);

            ReviewResponseDTO.ReviewDetailDTO reviewDetailDTO = ReviewConverter.toReviewDetailDTO(review);

            return ApiResponse.onSuccess(reviewDetailDTO);

        } catch (Exception e) {
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @GetMapping("/{coachId}")
    @Operation(summary = "동네형 마이페이지")
    public ApiResponse<CoachResponseDTO.CoachMyPageDTO> getCoachMyPage (@PathVariable(value = "coachId") Long coachId){
        return ApiResponse.onSuccess(CoachConverter.toCoachMyPageDTO(coachService.getCoachById(coachId), registerService.getMatchNumCoach(coachId), reviewService.getReviewNumCoach(coachId)));
    }

    @PatchMapping("/{coachId}")
    @Operation(summary = "동네형 정보 수정")
    public ApiResponse<CoachResponseDTO.CoachUpdateResponseDTO> patchCoachUpdate(@PathVariable(value = "coachId") Long coachId, @RequestBody CoachRequestDTO.CoachUpdateRequestDTO
            coachUpdateRequestDTO){
        Coach coach = coachService.updateCoach(coachId, coachUpdateRequestDTO);
        return ApiResponse.onSuccess(CoachConverter.toCoachUpdateDTO(coach));
    }

    @PatchMapping(value = "/{coachId}/sign-up", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "동네형 회원가입 완료 후 첫 정보 입력 페이지")
    public ApiResponse<String> coachSignUp(@RequestPart(value = "request") CoachRequestDTO.CoachProfileRegisterDTO request,
                                           @RequestPart(value = "picture", required = false) MultipartFile picture,
                                           @RequestPart(value = "album", required = false) List<MultipartFile> pictureList,
                                           @PathVariable(value = "coachId") Long coachId){
        try{
            coachService.insertCoachInfo(coachId, request);
            if(picture != null) coachService.insertCoachPicture(coachId, picture);  // 동네형 프로필 사진이 주어졌을 때
            if(pictureList != null) coachService.insertCoachAlbum(coachId,pictureList); // 동네형 사진첩 이미지 등록

            return ApiResponse.onSuccess("동네형의 정보가 성공적으로 입력되었습니다.");

        } catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @GetMapping("/album/{coachId}")
    @Operation(summary = "동네형의 사진첩을 조회하는 API")
    public ApiResponse<CoachResponseDTO.CoachAlbumDTO> getCoachAlbum(@PathVariable(value = "coachId") Long coachId) {

        try {
            Coach coach = coachService.getCoachById(coachId);

            CoachResponseDTO.CoachAlbumDTO coachAlbumDTO = CoachConverter.toCoachAlbumDTO(coach);

            return ApiResponse.onSuccess(coachAlbumDTO);

        } catch (Exception e) {
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }


}
