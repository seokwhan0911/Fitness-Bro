package FitnessBro.converter;

import FitnessBro.web.dto.LoginDTO;

public class LoginConverter {

    public static LoginDTO loginDTO(String userToken, Long userId){
        return LoginDTO.builder()
                .userId(userId)
                .userToken(userToken)
                .build();
    }
}
