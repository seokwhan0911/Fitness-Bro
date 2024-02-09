package FitnessBro.web.controller;


import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.CoachConverter;
import FitnessBro.converter.ReviewConverter;
import FitnessBro.domain.Coach;
import FitnessBro.domain.Review;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.service.LoginService.LoginService;
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
    private final LoginService loginService;

    @GetMapping("/info")
    @Operation(summary = "코치 상세정보 API", description = "코치 id를 받아 코치 상세정보 전달")
    public ResponseEntity<ApiResponse<CoachResponseDTO.CoachProfileDTO>> getCoachInfo(@RequestHeader(value = "token") String token) {
        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(CoachConverter.toCoachProfileDTO(coachService.getCoachById(userId))));
    }


    //헬스장 id를 받지 않고 그냥 다 넘겨 줄 때
    @GetMapping("/search")
    @Operation(summary = "동네형 리스트 API", description = "동네형 리스트 전달")
    public ResponseEntity<ApiResponse<List<CoachResponseDTO.CoachDTO>>> getCoachList() {

        List<Coach> coachList = coachService.getCoachList();
        ApiResponse<List<CoachResponseDTO.CoachDTO>> apiResponse = ApiResponse.onSuccess(CoachConverter.toCoachListDTO(coachList));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/reviews")
    @Operation(summary = "동네형이 받은 리뷰들을 조회 하는 API")
    public ResponseEntity<ApiResponse<List<ReviewResponseDTO.ReviewByCoachDTO>>> getReviews(@RequestHeader(value = "token") String token) {

        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);

        try {
            List<Review> reviews = reviewService.getReviewsByCoachId(userId);

            List<ReviewResponseDTO.ReviewByCoachDTO> reviewDTOList = ReviewConverter.toReviewByCoachDTO(reviews);

            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(reviewDTOList));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null));
        }
    }

    @GetMapping("/reviews/{reviewId}")
    @Operation(summary = "동네형이 받은 리뷰 상세보기를 조회하는 API")
    public ResponseEntity<ApiResponse<ReviewResponseDTO.ReviewDetailDTO>> getReviewDetails(@PathVariable(value = "reviewId") Long reviewId) {

        try {
            Review review = reviewService.getReviewById(reviewId);

            ReviewResponseDTO.ReviewDetailDTO reviewDetailDTO = ReviewConverter.toReviewDetailDTO(review);

            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(reviewDetailDTO));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null));
        }
    }

    @GetMapping("")
    @Operation(summary = "코치 마이페이지")
    public ResponseEntity<ApiResponse<CoachResponseDTO.CoachMyPageDTO>> getCoachMyPage (@RequestHeader(value = "token") String token){
        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(CoachConverter.toCoachMyPageDTO(coachService.getCoachById(userId), registerService.getMatchNumCoach(userId), reviewService.getReviewNumCoach(userId))));
    }

    @PatchMapping("")
    @Operation(summary = "코치 정보 수정")
    public ResponseEntity<ApiResponse<CoachResponseDTO.CoachUpdateResponseDTO>> patchCoachUpdate(@RequestHeader(value = "token") String token, @RequestBody CoachRequestDTO.CoachUpdateRequestDTO
            coachUpdateRequestDTO){
        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);
        Coach coach = coachService.updateCoach(userId, coachUpdateRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(CoachConverter.toCoachUpdateDTO(coach)));
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

    @GetMapping("/album")
    @Operation(summary = "동네형의 사진첩을 조회하는 API")
    public ResponseEntity<ApiResponse<CoachResponseDTO.CoachAlbumDTO>> getCoachAlbum(@RequestHeader(value = "token") String token) {

        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);

        try {
            Coach coach = coachService.getCoachById(userId);

            CoachResponseDTO.CoachAlbumDTO coachAlbumDTO = CoachConverter.toCoachAlbumDTO(coach);

            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(coachAlbumDTO));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null));
        }
    }


}
