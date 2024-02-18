package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.RegisterConverter;
import FitnessBro.domain.Coach;
import FitnessBro.domain.Member;
import FitnessBro.domain.Register;
import FitnessBro.domain.RegisterStatus;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.service.LoginService.LoginService;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.service.RegisterService.RegisterService;
import FitnessBro.web.dto.RegisterRequestDTO;
import FitnessBro.web.dto.RegisterResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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




    @PostMapping("/member")
    @Operation(summary = "유저가 먼저 성사버튼 클릭", description = "유저가 채팅에서 '성사 완료' 버튼 누를때 api")
    public ResponseEntity<RegisterStatus> getRegisterMember(@RequestHeader(value = "token") String token, @RequestBody @Valid RegisterRequestDTO.CoachRequestDTO request){

        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);

        Member member = memberCommandService.getMemberById(userId);
        Coach coach = coachService.getCoachById(request.getCoachId());

        Register register = registerService.registerSetting(member, coach);

        return ResponseEntity.ok().body(register.getRegisterStatus());
    }

    @PostMapping("/coach/approve")
    @Operation(summary = "유저가 먼저 요청한 성사 요청 -> 코치가 성사버튼 클릭", description = "코치가 요청에대한 '수락' 버튼 누를때 api")
    public ResponseEntity<RegisterStatus> getApproveRegisterCoach(@RequestHeader(value = "token")String token, @RequestBody @Valid RegisterRequestDTO.MemberRequestDTO request){

        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);

        Member member = memberCommandService.getMemberById(request.getMemberId());
        Coach coach = coachService.getCoachById(userId);

        Register register = registerService.registerApproveSetting(member, coach);

        return ResponseEntity.ok().body(register.getRegisterStatus());
    }

    @PostMapping("/coach/reject")
    @Operation(summary = "유저가 먼저 요청한 성사 요청 -> 코치가 거절버튼 클릭", description = "코치가 요청에 대한 '거절' 버튼 누를때 api")
    public ResponseEntity<RegisterStatus> setRejectRegisterCoach(@RequestHeader(value = "token")String token, @RequestBody @Valid RegisterRequestDTO.MemberRequestDTO request){

        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);

        Member member = memberCommandService.getMemberById(request.getMemberId());
        Coach coach = coachService.getCoachById(userId);

        Register register = registerService.registerRejectSetting(member, coach);

        return ResponseEntity.ok().body(register.getRegisterStatus());
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
