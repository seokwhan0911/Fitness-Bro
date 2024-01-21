package FitnessBro.web.controller;


import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.apiPayload.code.status.ErrorStatus;
import FitnessBro.converter.CoachConverter;
import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.web.dto.CoachResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static FitnessBro.apiPayload.Utill.ValidationUtils.getValidationErrors;


@RestController
@RequestMapping("/coaches")
@RequiredArgsConstructor
public class CoachController {
    private final CoachService coachService;


    @GetMapping("/{coachId}/info")
    @Operation(summary = "코치 마이페이지 API", description = "아이디를 받아 코치 마이페이지 전달")
    public ApiResponse<CoachResponseDTO.CoachProfileDTO> getCoachInfo(@PathVariable(value = "coachId") Long coachId, Errors errors){
        coachService.addCoach();
        return ApiResponse.onSuccess(CoachConverter.toCoachProfileDTO(coachService.getCoachById(coachId)));
    }

    @GetMapping("/search")
    @Operation(summary = "코치 리스트 API", description = "코치 리스트 전달")
    public ResponseEntity<ApiResponse<CoachResponseDTO.CoachListDTO>> getCoachList(){
        ApiResponse<CoachResponseDTO.CoachListDTO> = CoachService.
        return null;
    }

}
