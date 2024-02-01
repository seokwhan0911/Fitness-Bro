package FitnessBro.service.MemberService;

import FitnessBro.apiPayload.Utill.JwtTokenUtil;
import FitnessBro.apiPayload.code.status.ErrorStatus;
import FitnessBro.apiPayload.exception.AppException;
import FitnessBro.converter.MemberConverter;
import FitnessBro.domain.member.Entity.Member;
import FitnessBro.respository.MemberRepository;
import FitnessBro.respository.RegisterRepository;
import FitnessBro.respository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;
    @Override
    public Member getMemberById(Long memberId){
        Member member = memberRepository.getById(memberId);
        return member;
    }




    @Value("${jwt.secret}")
    private String key;
    private Long expireTimeMs = 1000 *60 * 60l;

    @Override
    @Transactional
    public String joinMember(MemberRequestDTO.JoinDTO request) {

        // member 중복 체크
        memberRepository.findByEmail(request.getEmail())
                .ifPresent(member -> {
                    throw new AppException(ErrorStatus.EMAIL_DUPLICATED, request.getEmail() + "는 이미 있습니다.");
                });

        Member member = MemberConverter.toMember(request);

        member.setPassword(encoder.encode(request.getPassword()));

        memberRepository.save(member);

        return "SUCCESS";
    }

    @Override
    @Transactional
    public String login(String email, String password){
        // Email 없음
        Optional<Member> selectedMember = memberRepository.findByEmail(email);
        Member member = selectedMember.orElseThrow(() -> new AppException(ErrorStatus.EMAIL_NOT_FOUND, "해당 멤버를 찾을 수 없습니다."));

        // password 틀림
        if(!encoder.matches(password, member.getPassword())){
            throw new AppException(ErrorStatus.INVALID_PASSWORD, "패스워드를 잘못 입력 했습니다.");
        }
        //앞에서 Exception 안났으면 토큰 발행
        String token = JwtTokenUtil.createToken(member.getEmail(), key,expireTimeMs);
        return token;
    }
}
