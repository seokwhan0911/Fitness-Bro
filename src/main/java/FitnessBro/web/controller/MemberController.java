package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.CoachConverter;
import FitnessBro.converter.MemberConverter;
import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.member.Entity.Member;
import FitnessBro.domain.register.Entity.Register;
import FitnessBro.domain.Coach;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.service.RegisterService.RegisterService;
import FitnessBro.service.ReviewService.ReviewService;
import FitnessBro.web.dto.Member.MemberRequestDTO;
import FitnessBro.web.dto.ReviewRequestDTO;
import FitnessBro.web.dto.ReviewResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import FitnessBro.service.MemberService.MemberQueryService;
import FitnessBro.service.ReviewService.ReviewService;
import FitnessBro.web.dto.Coach.CoachResponseDTO;
import FitnessBro.web.dto.Member.MemberResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final ReviewService reviewService;
    private final RegisterService registerService;
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



    @GetMapping("/{memberId}")
    @Operation(summary = "유저 마이페이지")
    public ApiResponse<MemberResponseDTO.MemberMyPageDTO> getMemberMyPage(@PathVariable(value = "memberId") Long memberId) {
        return ApiResponse.onSuccess(MemberConverter.toMemberMyPageDTO(memberCommandService.getMemberById(memberId),registerService.getMatchNumMember(memberId),reviewService.getReviewNumCoach(memberId)));
    }
    @GetMapping("/{userId}/reviews")
    public ApiResponse<List<ReviewResponseDTO.ReviewByUserDTO>> getReviewsByUser(@PathVariable(value = "userId") Long userId ){
        return ApiResponse.onSuccess(reviewService.getReviews(userId));
    }

    @PostMapping(value = "/{userId}/reviews", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "사용자가 동네형에게 리뷰를 작성하는 API")
    public ApiResponse<String> createReviews(@RequestPart ReviewRequestDTO.CreateReviewDTO request,
                                             @RequestPart(value ="files", required = false) List<MultipartFile> files,
                                             @PathVariable(value = "userId") Long userId ){

        if(files != null) { // 리뷰에 이미지가 포함되어 있는 경우
            reviewService.createReviewWithFiles(request, files, userId);
        } else {    // 리뷰에 미지가 포함되어 있지 않은 경우
            reviewService.createReview(request,userId);
        }

        return ApiResponse.onSuccess("성공적으로 리뷰 작성을 했습니다.");
    }

    // 회원 반환 임시 메서드
    private Long getCurrentMemberId(){
        return 1l;
    }

    @PatchMapping("/{memberId}")
    @Operation(summary = "멤버 정보 수정")
    public ApiResponse<MemberResponseDTO.MemberUpdateResponseDTO> patchCoachUpdate(@PathVariable(value = "memberId") Long memberId, @RequestBody MemberRequestDTO.MemberUpdateRequestDTO memberUpdateRequestDTO) {
        Member member = memberCommandService.updateMember(memberId,memberUpdateRequestDTO);
        return ApiResponse.onSuccess(MemberConverter.toMemberUpdateDTO(member));
    }
}
