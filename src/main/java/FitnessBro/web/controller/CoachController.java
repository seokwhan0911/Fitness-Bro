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

    //헬스장 id를 받지 않고 그냥 다 넘겨 줄 때
    @GetMapping("/search")
    @Operation(summary = "동네형 리스트 조회하기 API", description = "로그인하지 않은 사용자 조회 가능")
    public ResponseEntity<ApiResponse<List<CoachResponseDTO.CoachDTO>>> getCoachList() {

        try {
            List<Coach> coachList = coachService.getCoachList();
            ApiResponse<List<CoachResponseDTO.CoachDTO>> apiResponse = ApiResponse.onSuccess(CoachConverter.toCoachListDTO(coachList));

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

        } catch (Exception e) {
            ApiResponse<List<CoachResponseDTO.CoachDTO>> apiResponse = ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

    @GetMapping("/{coachId}/info")
    @Operation(summary = "동네형 상세 정보 조회하기 API", description = "동네형 id(coachId)를 받아 동네형 상세 정보 전달, 로그인하지 않은 사용자도 조회 가능")
    public ResponseEntity<ApiResponse<CoachResponseDTO.CoachProfileDTO>> getCoachInfo(@PathVariable(value = "coachId") Long coachId) {
        try {
            Coach coach = coachService.getCoachById(coachId);

            CoachResponseDTO.CoachProfileDTO coachProfileDTO = CoachConverter.toCoachProfileDTO(coach);

            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(coachProfileDTO));

        }catch (Exception e){
            ApiResponse<CoachResponseDTO.CoachProfileDTO> apiResponse = ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }


    @GetMapping("/{coachId}/reviews")
    @Operation(summary = "동네형이 받은 리뷰들을 조회 하는 API", description = "동네형 id(coachId)를 받아 동네형이 받은 리뷰 리스트 전달, 로그인하지 않은 사용자도 조회 가능")
    public ResponseEntity<ApiResponse<List<ReviewResponseDTO.ReviewByCoachDTO>>> getReviews(@PathVariable(value = "coachId") Long coachId) {

        try {
            List<Review> reviews = reviewService.getReviewsByCoachId(coachId);

            List<ReviewResponseDTO.ReviewByCoachDTO> reviewDTOList = ReviewConverter.toReviewByCoachDTO(reviews);

            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(reviewDTOList));

        } catch (Exception e){
            ApiResponse<List<ReviewResponseDTO.ReviewByCoachDTO>> apiResponse = ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }

    }

    @GetMapping("/reviews/{reviewId}")
    @Operation(summary = "동네형이 받은 리뷰 상세보기를 조회하는 API", description = "리뷰 id(reviewId)를 받아 동네형이 받은 리뷰 상세 정보 전달, 로그인하지 않은 사용자도 조회 가능")
    public ResponseEntity<ApiResponse<ReviewResponseDTO.ReviewDetailDTO>> getReviewDetails(@PathVariable(value = "reviewId") Long reviewId) {

        try {
            Review review = reviewService.getReviewById(reviewId);

            ReviewResponseDTO.ReviewDetailDTO reviewDetailDTO = ReviewConverter.toReviewDetailDTO(review);

            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(reviewDetailDTO));

        } catch (Exception e) {
            ApiResponse<ReviewResponseDTO.ReviewDetailDTO> apiResponse = ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

    @GetMapping("/album/{coachId}")
    @Operation(summary = "동네형의 사진첩을 조회하는 API", description = "동네형 id(coachId)를 받아 동네형의 사진첩 조회, 로그인하지 않은 사용자도 조회 가능")
    public ResponseEntity<ApiResponse<CoachResponseDTO.CoachAlbumDTO>> getCoachAlbum(@PathVariable(value = "coachId") Long coachId) {

        try {
            Coach coach = coachService.getCoachById(coachId);

            CoachResponseDTO.CoachAlbumDTO coachAlbumDTO = CoachConverter.toCoachAlbumDTO(coach);

            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(coachAlbumDTO));

        } catch (Exception e) {
            ApiResponse<CoachResponseDTO.CoachAlbumDTO> apiResponse = ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

    @GetMapping("/my-page")
    @Operation(summary = "동네형 마이페이지 API")
    public ResponseEntity<ApiResponse<CoachResponseDTO.CoachMyPageDTO>> getCoachMyPage(@RequestHeader(value = "token") String token){

        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);
        System.out.println(userId);

        try {
            Coach coach = coachService.getCoachById(userId);
            Long matchNum = registerService.getMatchNumCoach(userId);
            Long reviewNum = reviewService.getReviewNumCoach(userId);

            CoachResponseDTO.CoachMyPageDTO coachMyPageDTO = CoachConverter.toCoachMyPageDTO(coach, matchNum, reviewNum);

            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(coachMyPageDTO));
        } catch (Exception e){
            ApiResponse<CoachResponseDTO.CoachMyPageDTO> apiResponse = ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

    @GetMapping("/my-info")
    @Operation(summary = "동네형 내 정보 조회하기 API")
    public ResponseEntity<ApiResponse<CoachResponseDTO.CoachMyInfoDTO>> getCoachMyInfo(@RequestHeader(value = "token") String token){

        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);

        try {
            Coach coach = coachService.getCoachById(userId);
            CoachResponseDTO.CoachMyInfoDTO coachMyInfoDTO = CoachConverter.toCoachMyInfoDTO(coach);

            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(coachMyInfoDTO));

        } catch (Exception e){
            ApiResponse<CoachResponseDTO.CoachMyInfoDTO> apiResponse = ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

    @PostMapping(value = "/sign-up", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "동네형 회원가입 완료 후 첫 정보 입력 API")
    public ResponseEntity<ApiResponse<String>> coachSignUp(@RequestPart(value = "request") CoachRequestDTO.CoachProfileRegisterDTO request,
                                                           @RequestPart(value = "picture", required = false) MultipartFile picture,
                                                           @RequestPart(value = "album", required = false) List<MultipartFile> pictureList,
                                                           @RequestHeader(value = "token") String token){
        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);

        try {
            coachService.insertCoachInfo(userId, request);
            if(picture != null) coachService.insertCoachPicture(userId, picture);  // 동네형 프로필 사진이 주어졌을 때
            if(pictureList != null) coachService.insertCoachAlbum(userId,pictureList); // 동네형 사진첩 이미지 등록

            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess("동네형의 정보가 성공적으로 입력되었습니다."));
        } catch (Exception e){
            ApiResponse<String> apiResponse = ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "동네형 내 정보 수정하기 API")
    public ResponseEntity<ApiResponse<String>> coachUpdate(@RequestPart(value = "request") CoachRequestDTO.CoachProfileRegisterDTO request,
                                                                                                 @RequestPart(value = "picture", required = false) MultipartFile picture,
                                                                                                 @RequestPart(value = "album", required = false) List<MultipartFile> pictureList,
                                                                                                 @RequestHeader(value = "token") String token){
        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);

        try {
            coachService.insertCoachInfo(userId, request);
            if(picture != null) coachService.insertCoachPicture(userId, picture);  // 동네형 프로필 사진이 주어졌을 때
            if(pictureList != null) coachService.insertCoachAlbum(userId,pictureList); // 동네형 사진첩 이미지 등록

            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess("동네형의 정보가 성공적으로 입력되었습니다."));
        } catch (Exception e){
            ApiResponse<String> apiResponse = ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }


}
