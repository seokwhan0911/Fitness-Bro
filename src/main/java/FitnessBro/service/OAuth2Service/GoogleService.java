package FitnessBro.service.OAuth2Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleService {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String GOOGLE_SOCIAL_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String GOOGLE_SOCIAL_CLIENT_SECRET;

    private final String GOOGLE_SOCIAL_LOGIN_URL = "https://accounts.google.com/o/oauth2/v2/auth";
    private final String GOOGLE_SOCIAL_REDIRECT_URL = "http://localhost:8080/login/oauth2/code/google";
    private final String GOOGLE_SOCIAL_TOKEN_URL = "https://oauth2.googleapis.com/token";
    private final String GOOGLE_SOCIAL_API = "https://www.googleapis.com/oauth2/v2/userinfo";

    public String getOauthRedirectURL() {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("scope", "https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile");
        queryParams.set("response_type", "code");
        queryParams.set("client_id", GOOGLE_SOCIAL_CLIENT_ID);
        queryParams.set("redirect_uri", GOOGLE_SOCIAL_REDIRECT_URL);

        //MultiValueMap으로 매개변수 완성하고 UriComponentsBuilder로 빌더패턴으로 uri 생성후 .toString()으로 문자열로 반환.
        return UriComponentsBuilder
                .fromUriString(GOOGLE_SOCIAL_LOGIN_URL)
                .queryParams(queryParams)
                .encode().build().toString();
    }

    public ResponseEntity<String> requestAccessToken(String code) {
        RestTemplate restTemplate=new RestTemplate();

        //MultiValueMap으로 매개변수 설정.
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("code", code);
        queryParams.set("client_id", GOOGLE_SOCIAL_CLIENT_ID);
        queryParams.set("redirect_uri", GOOGLE_SOCIAL_REDIRECT_URL);
        queryParams.set("client_secret", GOOGLE_SOCIAL_CLIENT_SECRET);
        queryParams.set("grant_type", "authorization_code");
        //RestTemplate 방식으로 Post. URL, 매개변수, 반환값 설정 가능.
        return restTemplate.postForEntity(GOOGLE_SOCIAL_TOKEN_URL, queryParams, String.class);
    }

    public HashMap<String, String> getUserInfo(String accessTokenResponseBody) {
        //accessToken이 포함된 response body(json파일)를 전달받아서 파싱후 access_token 추출
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(accessTokenResponseBody);
        String accessToken = element.getAsJsonObject().get("access_token").getAsString();

        //RestTemplate으로 Google API 서버에 유저 정보를 HTTP GET 요청
        RestTemplate restTemplate = new RestTemplate();

        //HttpHeaders : RestTemplate 방식으로 HTTP 요청할때 header 설정 가능
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);  // Bearer Token으로 인증 헤더 설정

        //HttpEntity에는 body가 string으로 들어가고, header도 들어감.
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //restTemplate.exchange로 HTTP 요청을 보냄. 안에서 url, http 메서드, 내용(entity), 반환값을 설정할 수 있음
        ResponseEntity<String> response = restTemplate.exchange(
                GOOGLE_SOCIAL_API,
                HttpMethod.GET,
                entity,
                String.class  // JSON 응답을 String 변환
        );

        //json 파싱해서 id, email userInfo에 저장
        HashMap<String, String> userInfo = new HashMap<>();
        JsonElement resp = parser.parse(Objects.requireNonNull(response.getBody()));
        String id = resp.getAsJsonObject().get("id").getAsString();
        String email = resp.getAsJsonObject().get("email").getAsString();

        System.out.println("id = " + id);
        System.out.println("email = " + email);

        userInfo.put("id", "google_" + id);
        userInfo.put("email", email);

        return userInfo;
    }

}
