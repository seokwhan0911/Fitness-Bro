package FitnessBro.service.UserService;

import FitnessBro.converter.UserConverter;
import FitnessBro.domain.user.Entity.Member;
import FitnessBro.respository.MemberRepository;
import FitnessBro.web.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;

    @Override
    public Member getMemberById(Long memberId){
        Member member = memberRepository.getById(memberId);
        return member;
    }



}
