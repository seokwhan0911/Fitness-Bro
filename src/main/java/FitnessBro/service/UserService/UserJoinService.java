package FitnessBro.service.UserService;


import FitnessBro.domain.user.Entity.Member;
import FitnessBro.respository.MemberRepository;
import FitnessBro.web.dto.UserJoinDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserJoinService {


    private final MemberRepository memberRepository;

    public void joinProcess(UserJoinDTO userJoinDTO){

        String useremail = userJoinDTO.getEmail();
        String password = userJoinDTO.getPassword();

        boolean isExist = memberRepository.existsByEmail(useremail);

        if(isExist){
            return;
        }

        Member member = new Member();


    }
}
