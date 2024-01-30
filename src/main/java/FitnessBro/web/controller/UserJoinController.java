package FitnessBro.web.controller;


import FitnessBro.web.dto.UserJoinDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class UserJoinController {

    @PostMapping("users/sign-up")
    public String joinProcess(UserJoinDTO userJoinDTO){
        return "ok";
    }
}
