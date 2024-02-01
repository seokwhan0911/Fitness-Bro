package FitnessBro.web.controller;

import FitnessBro.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final KakaoService kakaoService;
    @GetMapping
    public String login(){
        return "login";
    }


    @GetMapping("/oauth2/code/kakao")
    public String getToken(@RequestParam("code") String code)  {
        ResponseEntity<String> stringResponseEntity = kakaoService.getKakaoAccessToken(code);

        log.info(stringResponseEntity.getBody());

        return stringResponseEntity.getBody();
    }




}
