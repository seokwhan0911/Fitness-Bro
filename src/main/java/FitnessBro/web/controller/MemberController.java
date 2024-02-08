package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.CoachConverter;
import FitnessBro.domain.Coach;
import FitnessBro.domain.Member;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.service.ReviewService.ReviewService;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import FitnessBro.web.dto.Member.MemberRequestDTO;
import FitnessBro.web.dto.review.ReviewRequestDTO;
import FitnessBro.web.dto.review.ReviewResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import FitnessBro.service.MemberService.MemberQueryService;
import FitnessBro.web.dto.Coach.CoachResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final ReviewService reviewService;
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    private Long getCurrentMemberId(){
        return 1l;
    }
    @GetMapping("/favorites")
    @Operation(summary = "사용자가 찜한 동네형 목록 조회 API")
    public ResponseEntity<ApiResponse<List<CoachResponseDTO.favoriteCoachDTO>>> getFavoriteCoachList(){
        try{
            Long memberId = getCurrentMemberId();

            // 찜한 동네형 목록 조회
            List<Coach> coachList = memberQueryService.getFavoriteCoachList(memberId);

            // 찜한 동네형 목록을 사용하여 DTO로 반환
            List<CoachResponseDTO.favoriteCoachDTO> favoriteCoachDTOList = coachList.stream()
                    .map(CoachConverter::toFavoriteCoachDTO)
                    .collect(Collectors.toList());

            // 성공 응답 생성
            ApiResponse<List<CoachResponseDTO.favoriteCoachDTO>> apiResponse = ApiResponse.onSuccess(favoriteCoachDTOList);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

        } catch (Exception e){
            ApiResponse<List<CoachResponseDTO.favoriteCoachDTO>> apiResponse = ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);

        }
    }

    @PostMapping("{userId}/favorite/{coachId}")
    @Operation(summary = "사용자가 찜한 형 등록하기 API", description = "사용자가 찜하려는 동네형의 아이디를 입력해주세요.")
    public ResponseEntity<ApiResponse<String>> createFavoriteCoach(@PathVariable(value = "userId") Long userId,
                                                                   @PathVariable(value = "coachId") Long coachId) {

        try {
            memberCommandService.createFavoriteCoach(userId, coachId);

            ApiResponse<String> apiResponse = ApiResponse.onSuccess("동네형 찜 등록을 성공했습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

        } catch (Exception e) {
            ApiResponse<String> apiResponse = ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }



    @GetMapping("/{userId}/reviews")
    @Operation(summary = "사용자가 작성한 후기 리스트 조회 API")
    public ResponseEntity<ApiResponse<List<ReviewResponseDTO.ReviewByUserDTO>>> getReviewsByUser(@PathVariable(value = "userId") Long userId ){

        try {
            ApiResponse<List<ReviewResponseDTO.ReviewByUserDTO>> apiResponse = ApiResponse.onSuccess(reviewService.getReviews(userId));
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }catch (Exception e){
            ApiResponse<List<ReviewResponseDTO.ReviewByUserDTO>> apiResponse = ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

    @PostMapping(value = "/{userId}/reviews", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "사용자가 동네형에게 리뷰를 작성하는 API")
    public ResponseEntity<ApiResponse<String>> createReviews(@RequestPart(value = "request") ReviewRequestDTO.CreateReviewDTO request,
            @RequestPart(value ="files", required = false) List<MultipartFile> files,
            @PathVariable(value = "userId") Long userId ){
        try{
            if(files != null) { // 리뷰에 이미지가 포함되어 있는 경우
                reviewService.createReviewWithFiles(request, files, userId);
            } else {    // 리뷰에 미지가 포함되어 있지 않은 경우
                reviewService.createReview(request,userId);
            }
            ApiResponse<String> apiResponse = ApiResponse.onSuccess("성공적으로 리뷰 작성을 했습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

        }catch (Exception e){
            ApiResponse<String> apiResponse = ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

//    @PostMapping("/sign-up")
//    @Operation(summary = "회원가입")
//    public ResponseEntity<String> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request){
//        memberCommandService.joinMember(request);
//        return ResponseEntity.ok().body("회원가입에 성공했습니다.");
//    }
//
//
//    @PostMapping("/login")
//    @Operation(summary = "로그인")
//    public ResponseEntity<String> login(@RequestBody @Valid MemberRequestDTO.loginDTO request) {
//        String token = memberCommandService.login(request.getEmail(), request.getPassword());
//
//        return ResponseEntity.ok().body(token);
//    }

    // 회원 반환 임시 메서드



    @PutMapping("/{memberId}/sign-up")
    @Operation(summary = "유저 회원가입 완료 후 첫 정보 입력 페이지")
    public ApiResponse<String> coachSignUp(@PathVariable(value = "memberId") Long memberId,
                                           @RequestBody @Valid MemberRequestDTO.MemberProfileRegisterDTO request){

        Optional<Member> member = memberCommandService.insertInfo(memberId, request);

        return ApiResponse.onSuccess("Success");

    }
}
