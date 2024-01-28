package FitnessBro.web.controller;

import FitnessBro.domain.user.Entity.Member;
import FitnessBro.service.UserService.MemberCommandService;
import FitnessBro.web.dto.MemberRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request){
        memberCommandService.joinMember(request);
        return ResponseEntity.ok().body("회원가입에 성공했습니다.");
    }
}
