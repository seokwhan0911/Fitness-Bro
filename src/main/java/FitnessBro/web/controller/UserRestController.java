package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.UserConverter;
import FitnessBro.domain.user.Entity.Member;
import FitnessBro.service.UserService.UserCommandService;
import FitnessBro.web.dto.ReviewResponseDTO;
import FitnessBro.web.dto.UserRequestDTO;
import FitnessBro.web.dto.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserCommandService userCommandService;

    @PostMapping("/")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDTO request){
        Member user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    @GetMapping("/{userId}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewByUserDTO> getReviewsByUser(@PathVariable(value = "userId") Long userId ){

    }
}
