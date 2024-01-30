package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.CoachConverter;
import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.user.Entity.Member;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.service.MemberService.MemberQueryService;
import FitnessBro.web.dto.Coach.CoachResponseDTO;
import FitnessBro.web.dto.Member.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memebers")
public class MemberRestController {

    private final MemberQueryService memberQueryService;

    @GetMapping("/{userId}/favorites")
    @Operation(summary = "사용자가 찜한 동네형 목록 조회 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
    })
    public ApiResponse<List<CoachResponseDTO.favoriteCoachDTO>> getFavoriteCoachList(@PathVariable(name = "member_id") Long memberId){
        try{
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


}
