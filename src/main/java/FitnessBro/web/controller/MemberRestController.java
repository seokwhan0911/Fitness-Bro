package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.CoachConverter;
import FitnessBro.converter.MemberConverter;
import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.service.ReviewService.ReviewService;
import FitnessBro.web.dto.Member.MemberRequestDTO;
import FitnessBro.web.dto.ReviewRequestDTO;
import FitnessBro.web.dto.ReviewResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import FitnessBro.service.MemberService.MemberQueryService;
import FitnessBro.service.ReviewService.ReviewService;
import FitnessBro.web.dto.Coach.CoachResponseDTO;
import FitnessBro.web.dto.Member.MemberResponseDTO;
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
public class MemberRestController {

    private final ReviewService reviewService;
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    @GetMapping("/favorites")
    @Operation(summary = "사용자가 찜한 동네형 목록 조회 API")
    public ApiResponse<List<CoachResponseDTO.favoriteCoachDTO>> getFavoriteCoachList(){
        try{
            Long memberId = getCurrentMemberId();

            // 찜한 동네형 목록 조회
            List<Coach> coachList = memberQueryService.getFavoriteCoachList(memberId);

            // 찜한 동네형 목록을 사용하여 DTO로 반환
            List<CoachResponseDTO.favoriteCoachDTO> favoriteCoachDTOList = coachList.stream()
                    .map(CoachConverter::toFavoriteCoachDTO)
                    .collect(Collectors.toList());

            // 성공 응답 생성
            return ApiResponse.onSuccess(favoriteCoachDTOList);

        } catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request){
        memberCommandService.joinMember(request);
        return ResponseEntity.ok().body("회원가입에 성공했습니다.");
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid MemberRequestDTO.loginDTO request) {
        String token = memberCommandService.login(request.getEmail(), request.getPassword());

        return ResponseEntity.ok().body(token);
    }

    private Long getCurrentMemberId(){
        return 1l;
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "유저 마이페이지")
    public ApiResponse<MemberResponseDTO.MemberMyPageDTO> getMemberMyPage(@PathVariable(value = "memberId") Long memberId) {
        return ApiResponse.onSuccess(MemberConverter.toMemberMyPageDTO(memberCommandService.getMemberById(memberId),memberCommandService.getMatchNum(memberId),memberCommandService.getReviewNum(memberId)));
    }
    @GetMapping("/{userId}/reviews")
    public ApiResponse<List<ReviewResponseDTO.ReviewByUserDTO>> getReviewsByUser(@PathVariable(value = "userId") Long userId ){
        return ApiResponse.onSuccess(reviewService.getReviews(userId));
    }

    @PostMapping("/{userId}/reviews")
    public ApiResponse<String> createReviews(
            @Valid @RequestBody ReviewRequestDTO.CreateReviewDTO createReviewDTO, @PathVariable(value = "userId") Long userId ){

        reviewService.createReview(createReviewDTO, userId);
        return ApiResponse.onSuccess("성공적으로 성공했습니다.");
    }

}
