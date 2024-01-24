package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.register.Entity.Register;
import FitnessBro.domain.user.Entity.Member;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.service.RegisterService.RegisterService;
import FitnessBro.web.dto.RegisterResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final CoachService coachService;
    private final RegisterService registerService;
    @GetMapping("/{coachId}")
    @Operation(summary = " 유저 성사 API", description = "코치마이페이지에서 '우리회원성사리스트' 클릭시 나타나는 유저 리스트")
    public ResponseEntity<ApiResponse<List<RegisterResponseDTO.RegisterMemberDTO>>> getUserMatchList(@PathVariable(value = "coachId") Long coachId){

        Coach coach = coachService.getCoachById(coachId);


        return null;
    }


}
