package FitnessBro.service.OAuth2Service;

import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoService {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String KAKAO_SOCIAL_CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String KAKAO_SOCIAL_CLIENT_SECRET;


    public ResponseEntity<String> getKakaoAccessToken (String code)  {
        String REQUEST_URL = "https://kauth.kakao.com/oauth/token";
        RestTemplate restTemplate=new RestTemplate();

        // Set Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", "application/json");

        // Set parameter
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", KAKAO_SOCIAL_CLIENT_ID);
        params.add("redirect_uri", "http://localhost:8080/login/oauth2/code/kakao");
        params.add("code", code);
        // Set http entity
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(REQUEST_URL, request, String.class);



        return stringResponseEntity;
    }

    public HashMap<String, Object> getUserInfo(String token) {
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(token);
        String accessToken = element.getAsJsonObject().get("access_token").getAsString();

        HashMap<String, Object> userInfo = new HashMap<>();
        String reqUrl = "https://kapi.kakao.com/v2/user/me";
        try{
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            int responseCode = conn.getResponseCode();
            log.info("[KakaoApi.getUserInfo] responseCode : {}",  responseCode);

            BufferedReader br;
            if (responseCode >= 200 && responseCode <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line = "";
            StringBuilder responseSb = new StringBuilder();
            while((line = br.readLine()) != null){
                responseSb.append(line);
            }
            String result = responseSb.toString();
            log.info("responseBody = {}", result);

            JsonParser parser1 = new JsonParser();
            JsonElement element1 = parser1.parse(result);

            String id= element1.getAsJsonObject().get("id").getAsString();

            JsonObject kakaoAccount = element1.getAsJsonObject().get("kakao_account").getAsJsonObject();
            String email = kakaoAccount.getAsJsonObject().get("email").getAsString();

            userInfo.put("id", "kakao_" + id);
            userInfo.put("email", email);

            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return userInfo;
    }


}
