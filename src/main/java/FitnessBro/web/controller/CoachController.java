package FitnessBro.web.controller;


import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.apiPayload.code.status.ErrorStatus;
import FitnessBro.converter.CoachConverter;
import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.web.dto.CoachResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static FitnessBro.apiPayload.Utill.ValidationUtils.getValidationErrors;


@RestController
@RequestMapping("/coaches")
@RequiredArgsConstructor
public class CoachController {
    private final CoachService coachService;


    @GetMapping("/{coachId}/info")
    public ApiResponse<CoachResponseDTO.CoachProfileDTO> getCoachInfo(@PathVariable(value = "coachId") Long coachId, Errors errors){
        coachService.addCoach();
        return ApiResponse.onSuccess(CoachConverter.toCoachProfileDTO(coachService.getCoachById(coachId)));
    }

    @GetMapping("/{coachId}/reveiws")
    public ApiResponse<> getCoachReviews(@PathVariable(value = "coachId") Long coachId){

    }


}
