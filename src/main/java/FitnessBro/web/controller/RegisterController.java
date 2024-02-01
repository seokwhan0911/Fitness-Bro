package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.RegisterConverter;
import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.register.Entity.Register;
import FitnessBro.domain.member.Entity.Member;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.service.RegisterService.RegisterService;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.web.dto.RegisterResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class RegisterController {

    private final CoachService coachService;
    private final RegisterService registerService;
    private final MemberCommandService memberCommandService;

    @GetMapping("/coach/success/{coachId}")
    @Operation(summary = " 유저 성사 리스트 API", description = "코치마이페이지에서 '우리회원성사리스트' 클릭시 나타나는 유저 리스트")
    public ResponseEntity<ApiResponse<List<RegisterResponseDTO.RegisterMemberDTO>>> getMemberMatchList(@PathVariable(value = "coachId") Long coachId){

        Coach coach = coachService.getCoachById(coachId);
        List<Register> registerList = registerService.getRegisterListByCoach(coach);

        List<Register> trueRegisterList = registerService.successRegisterList(registerList);

        ApiResponse<List<RegisterResponseDTO.RegisterMemberDTO>> apiResponse = ApiResponse.onSuccess(RegisterConverter.toRegisterMemberListDTO(trueRegisterList));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/member/success/{memberId}")
    @Operation(summary = " 코치 성사 리스트 API", description = "유저 마이 페이지에서 '우리 형 성사 리스트' 클릭시 나타나는 코치 리스트")
    public ResponseEntity<ApiResponse<List<RegisterResponseDTO.RegisterCoachDTO>>> getCoachMatchList(@PathVariable(value = "memberId") Long memberId){

        Member member = memberCommandService.getMemberById(memberId);
        List<Register> registerList = registerService.getRegisterListByMember(member);

        List<Register> trueRegisterList = registerService.successRegisterList(registerList);

        ApiResponse<List<RegisterResponseDTO.RegisterCoachDTO>> apiResponse = ApiResponse.onSuccess(RegisterConverter.toRegisterCoachListDTO(trueRegisterList));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }




    @PostMapping("/member/{memberId}/{coachId}")
    @Operation(summary = "유저가 먼저 성사버튼 클릭", description = "유저가 채팅에서 '성사 완료' 버튼 누를때 api")
    public ResponseEntity<String> getRegisterMember(@PathVariable(value = "memberId") Long memberId, @PathVariable(value = "coachId") Long coachId){
        Member member = memberCommandService.getMemberById(memberId);
        Coach coach = coachService.getCoachById(coachId);

        Register register = registerService.registerSetting(member, coach);

        return ResponseEntity.ok().body("코치에게 성사 요청을 보냈습니다.");
    }

    @PostMapping("/coach/{coachId}/{memberId}")
    @Operation(summary = "유저가 먼저 요청한 성사 요청 -> 코치가 성사버튼 클릭", description = "코치가 채팅에서 '성사 완료' 버튼 누를때 api")
    public ResponseEntity<String> getRegisterCoach(@PathVariable(value = "coachId")Long coachId, @PathVariable(value = "memberId") Long memberId){
        Member member = memberCommandService.getMemberById(memberId);
        Coach coach = coachService.getCoachById(coachId);

        Register register = registerService.registerCoachSetting(member, coach);

        return ResponseEntity.ok().body(member.getNickname() + "님과 성사가 되었습니다.");
    }


}
