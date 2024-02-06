package FitnessBro.service.OAuth2Service;

import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
public class NaverService {

    public ResponseEntity<String> getKakaoAccessToken (String code, String state)  {
        String REQUEST_URL = "https://nid.naver.com/oauth2.0/token";
        RestTemplate restTemplate=new RestTemplate();

        // Set Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Set parameter
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "k7z58EUm09UZZxaxPTxk");
        params.add("client_secret", "X6Ovbl_IuM");
        params.add("redirect_uri", "http://localhost:8080/login/oauth2/code/naver");
        params.add("code", code);
        params.add("state", state);
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
        String reqUrl = "https://openapi.naver.com/v1/nid/me";
        try{
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

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

            JsonObject response = element1.getAsJsonObject().get("response").getAsJsonObject();

            String id = response.getAsJsonObject().get("id").getAsString();
            String email = response.getAsJsonObject().get("email").getAsString();

            userInfo.put("id","naver_"+id);
            userInfo.put("email", email);

            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return userInfo;
    }

}
