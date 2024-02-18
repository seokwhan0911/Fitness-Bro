package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.RegisterConverter;
import FitnessBro.domain.Coach;
import FitnessBro.domain.Member;
import FitnessBro.domain.Register;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.service.LoginService.LoginService;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.service.RegisterService.RegisterService;
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
    private final LoginService loginService;

    @GetMapping("/coach/success/")
    @Operation(summary = " 유저 성사 리스트 API", description = "코치마이페이지에서 '우리회원성사리스트' 클릭시 나타나는 유저 리스트")
    public ResponseEntity<ApiResponse<List<RegisterResponseDTO.RegisterMemberDTO>>> getMemberMatchList(@RequestHeader(value = "token")String token){

        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);

        Coach coach = coachService.getCoachById(userId);
        List<Register> registerList = registerService.getRegisterListByCoach(coach);

        List<Register> trueRegisterList = registerService.successRegisterList(registerList);

        ApiResponse<List<RegisterResponseDTO.RegisterMemberDTO>> apiResponse = ApiResponse.onSuccess(RegisterConverter.toRegisterMemberListDTO(trueRegisterList));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/member/success")
    @Operation(summary = " 코치 성사 리스트 API", description = "유저 마이 페이지에서 '우리 형 성사 리스트' 클릭시 나타나는 코치 리스트")
    public ResponseEntity<ApiResponse<List<RegisterResponseDTO.RegisterCoachDTO>>> getCoachMatchList(@RequestHeader(value = "token") String token){

        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);
        Member member = memberCommandService.getMemberById(userId);
        List<Register> registerList = registerService.getRegisterListByMember(member);

        List<Register> trueRegisterList = registerService.successRegisterList(registerList);

        ApiResponse<List<RegisterResponseDTO.RegisterCoachDTO>> apiResponse = ApiResponse.onSuccess(RegisterConverter.toRegisterCoachListDTO(trueRegisterList));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }




    @PostMapping("/member/{coachId}")
    @Operation(summary = "유저가 먼저 성사버튼 클릭", description = "유저가 채팅에서 '성사 완료' 버튼 누를때 api")
    public ResponseEntity<String> getRegisterMember(@RequestHeader(value = "token") String token, @PathVariable(value = "coachId") Long coachId){

        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);

        Member member = memberCommandService.getMemberById(userId);
        Coach coach = coachService.getCoachById(coachId);

        registerService.registerSetting(member, coach);

        return ResponseEntity.ok().body("코치에게 성사 요청을 보냈습니다.");
    }

    @PostMapping("/coach/approve/{memberId}")
    @Operation(summary = "유저가 먼저 요청한 성사 요청 -> 코치가 성사버튼 클릭", description = "코치가 요청에대한 '수락' 버튼 누를때 api")
    public ResponseEntity<String> getApproveRegisterCoach(@RequestHeader(value = "token")String token, @PathVariable(value = "memberId") Long memberId){

        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);

        Member member = memberCommandService.getMemberById(memberId);
        Coach coach = coachService.getCoachById(userId);

        registerService.registerApproveSetting(member, coach);

        return ResponseEntity.ok().body(member.getNickname() + "님과 성사가 되었습니다.");
    }

    @PostMapping("/coach/reject/{memberId}")
    @Operation(summary = "유저가 먼저 요청한 성사 요청 -> 코치가 거절버튼 클릭", description = "코치가 요청에 대한 '거절' 버튼 누를때 api")
    public ResponseEntity<String> setRejectRegisterCoach(@RequestHeader(value = "token")String token, @PathVariable(value = "memberId") Long memberId){

        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);

        Member member = memberCommandService.getMemberById(memberId);
        Coach coach = coachService.getCoachById(userId);

        registerService.registerRejectSetting(member, coach);

        return ResponseEntity.ok().body(member.getNickname() + "님의 요청을 거절 했습니다.");
    }



    @GetMapping("/coach/register-list")
    @Operation(summary = "코치한테 보낸 성사 요청 리스트 보기", description = "코치가 멤버가 보낸 성사 요청 리스트 보기 수락or거절")
    public ResponseEntity<ApiResponse<List<RegisterResponseDTO.RequestRegisterDTO>>> getRequestList(@RequestHeader(value = "token")String token){

        String userEmail = loginService.decodeJwt(token);
        Long coachId = loginService.getIdByEmail(userEmail);
        Coach coach = coachService.getCoachById(coachId);

        List<Register> registerList = registerService.getRegisterListByCoach(coach);

        List<Register> requestRegisterList = registerService.getRequestRegisterList(registerList);

        List<RegisterResponseDTO.RequestRegisterDTO> requestRegisterListDTO = RegisterConverter.toRequestRegisterListDTO(requestRegisterList);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(requestRegisterListDTO));
    }





}
