package FitnessBro.web.controller;

import FitnessBro.service.KakaoService;
import FitnessBro.service.MemberService.MemberCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final KakaoService kakaoService;
    private final MemberCommandService memberCommandService;
    @GetMapping
    public String loginPage(){
        return "login";
    }

    @GetMapping("/oauth2/code/kakao")
    public String KakaoLogin(@RequestParam("code") String code)  {
        ResponseEntity<String> stringResponseEntity = kakaoService.getKakaoAccessToken(code);

        log.info(stringResponseEntity.getBody());
        String token = stringResponseEntity.getBody();



        HashMap<String, Object> userInfo = kakaoService.getUserInfo(token);
        log.info(String.valueOf(userInfo));



        return stringResponseEntity.getBody();
    }

    @GetMapping("/oauth2/code/naver")
    public String NaverLogin(@RequestParam("code") String code)  {
        ResponseEntity<String> stringResponseEntity = kakaoService.getKakaoAccessToken(code);

        log.info(stringResponseEntity.getBody());
        String token = stringResponseEntity.getBody();


        HashMap<String, Object> userInfo = kakaoService.getUserInfo(token);
        log.info(String.valueOf(userInfo));

        return stringResponseEntity.getBody();
    }




}
