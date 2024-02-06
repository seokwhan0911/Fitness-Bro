package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.CoachConverter;

import FitnessBro.domain.Coach;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.service.ReviewService.ReviewService;
import FitnessBro.web.dto.Member.MemberRequestDTO;

import FitnessBro.web.dto.ReviewRequestDTO;
import FitnessBro.web.dto.ReviewResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import FitnessBro.service.MemberService.MemberQueryService;
import FitnessBro.web.dto.Coach.CoachResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final ReviewService reviewService;
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
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

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입")
    public ResponseEntity<String> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request){
        memberCommandService.joinMember(request);
        return ResponseEntity.ok().body("회원가입에 성공했습니다.");
    }
    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<String> login(@RequestBody @Valid MemberRequestDTO.loginDTO request) {
        String token = memberCommandService.login(request.getEmail(), request.getPassword());

        return ResponseEntity.ok().body(token);
    }

    private Long getCurrentMemberId(){
        return 1l;
    }

    @GetMapping("/{userId}/reviews")
    @Operation(summary = "내가 쓴 리뷰 리스트")
    public ResponseEntity<ApiResponse<List<ReviewResponseDTO.ReviewByUserDTO>>> getReviewsByUser(@PathVariable(value = "userId") Long userId ){
        try {
            ApiResponse<List<ReviewResponseDTO.ReviewByUserDTO>> apiResponse = ApiResponse.onSuccess(reviewService.getReviews(userId));
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        catch (Exception e){

            ApiResponse<List<ReviewResponseDTO.ReviewByUserDTO>> apiResponse = ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @PostMapping("/{userId}/reviews")
    @Operation(summary = "후기 쓰기")
    public ResponseEntity<ApiResponse<String>> createReviews(
            @Valid @RequestBody ReviewRequestDTO.CreateReviewDTO createReviewDTO, @PathVariable(value = "userId") Long userId ){

        reviewService.createReview(createReviewDTO, userId);
        ApiResponse<String> apiResponse = ApiResponse.onSuccess("성공적으로 후기를 작성했습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
