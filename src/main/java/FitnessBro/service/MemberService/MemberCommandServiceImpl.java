package FitnessBro.service.MemberService;

import FitnessBro.domain.member.Entity.Member;
import FitnessBro.respository.MemberRepository;
import FitnessBro.respository.RegisterRepository;
import FitnessBro.respository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    public final MemberRepository memberRepository;
    public final RegisterRepository registerRepository;
    public final ReviewRepository reviewRepository;

    public Member getMemberById(Long memberId){
        return memberRepository.getById(memberId);
    }
    public Long getReviewNum(Long memberId) {
        return reviewRepository.countByMemberId(memberId);
    }

    public Long getMatchNum(Long memberId) {
        return registerRepository.countByMemberId(memberId);
    }

}
