package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.service.OAuth2Service.KakaoService;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.service.OAuth2Service.NaverService;
import FitnessBro.web.dto.Coach.CoachResponseDTO;
import FitnessBro.web.dto.Login.LoginRequestDTO;
import FitnessBro.web.dto.Member.MemberRequestDTO;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final KakaoService kakaoService;
    private final NaverService naverService;
    private final MemberCommandService memberCommandService;

    @PostMapping("/select")
    public ResponseEntity<String> Select(@RequestHeader(value = "token") String token, @RequestBody @Valid LoginRequestDTO request){

        Claims userinfo = memberCommandService.decodeJwt(token);

//        memberCommandService.classifyUsers(userinfo, request.getRole());

        memberCommandService.classifyUsers(userinfo, request.getRole());

        return ResponseEntity.ok().body("select 성공");
    }



    @GetMapping("/oauth2/code/kakao")
    public ResponseEntity<String> KakaoLogin(@RequestParam("code") String code, @RequestBody @Valid MemberRequestDTO.JoinDTO request)  {



        ResponseEntity<String> stringResponseEntity = kakaoService.getKakaoAccessToken(code);

        String token = stringResponseEntity.getBody();
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(token);

        String userToken = memberCommandService.joinSocialMember(String.valueOf(userInfo.get("email")), String.valueOf(userInfo.get("id")));


        return ResponseEntity.ok().body(userToken);
    }

    @GetMapping("/oauth2/code/naver")
    public ResponseEntity<String> NaverLogin(@RequestParam("code") String code, @RequestParam("state") String state)  {


        ResponseEntity<String> stringResponseEntity = naverService.getNaverAccessToken(code, state);

        String token = stringResponseEntity.getBody();

        HashMap<String, Object> userInfo = naverService.getUserInfo(token);


        String userToken = memberCommandService.joinSocialMember(String.valueOf(userInfo.get("email")), String.valueOf(userInfo.get("id")));
        return ResponseEntity.ok().body(userToken);

    }




}
