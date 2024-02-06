package FitnessBro.web.controller;

import FitnessBro.service.OAuth2Service.KakaoService;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.service.OAuth2Service.NaverService;
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
    private final NaverService naverService;
    private final MemberCommandService memberCommandService;
    @GetMapping
    public String loginPage(){
        return "login";
    }

    @GetMapping("/oauth2/code/kakao")
    public ResponseEntity<String> KakaoLogin(@RequestParam("code") String code)  {

        // member entity member_id가 String이 아니라서 개판으로 짜임


        ResponseEntity<String> stringResponseEntity = kakaoService.getKakaoAccessToken(code);

        String token = stringResponseEntity.getBody();

        HashMap<String, Object> userInfo = kakaoService.getUserInfo(token);
        log.info(String.valueOf(userInfo));

        String userToken = memberCommandService.joinSocialMember(String.valueOf(userInfo.get("email")), String.valueOf(userInfo.get("id")));


        return ResponseEntity.ok().body(userToken);
    }

    @GetMapping("/oauth2/code/naver")
    public ResponseEntity<String> NaverLogin(@RequestParam("code") String code, @RequestParam("state") String state)  {

        // member entity member_id가 String이 아니라서 개판으로 짜임 

        ResponseEntity<String> stringResponseEntity = naverService.getKakaoAccessToken(code, state);

        String token = stringResponseEntity.getBody();

        HashMap<String, Object> userInfo = naverService.getUserInfo(token);
        log.info(String.valueOf(userInfo));

        String userToken = memberCommandService.joinSocialMember(String.valueOf(userInfo.get("email")), String.valueOf(userInfo.get("id")));

        return ResponseEntity.ok().body(userToken);

    }




}
