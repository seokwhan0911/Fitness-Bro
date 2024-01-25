package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.MemberConverter;
import FitnessBro.domain.member.Entity.Member;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.web.dto.MemberRequestDTO;
import FitnessBro.web.dto.MemberResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberRestController {

    private final MemberCommandService userCommandService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request){

        Member user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(user));
    }

    //@PostMapping("/sign-up")
    //public
}
