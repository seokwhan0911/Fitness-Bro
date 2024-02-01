package FitnessBro.service.MemberService;


import FitnessBro.domain.member.Entity.Member;

public interface MemberCommandService {

    public Member getMemberById(Long memberId);

    public Long getReviewNum(Long memberId);

    public Long getMatchNum(Long memberId);
}
