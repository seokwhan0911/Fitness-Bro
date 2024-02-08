package FitnessBro.service.MemberService;

import FitnessBro.apiPayload.Utill.JwtTokenUtil;
import FitnessBro.apiPayload.code.status.ErrorStatus;
import FitnessBro.apiPayload.exception.AppException;
import FitnessBro.converter.FavoriteConverter;
import FitnessBro.converter.MemberConverter;
import FitnessBro.domain.Coach;
import FitnessBro.domain.Favorites;
import FitnessBro.domain.Member;
import FitnessBro.respository.CoachRepository;
import FitnessBro.respository.FavoriteRepository;
import FitnessBro.respository.MemberRepository;
import FitnessBro.web.dto.Login.Role;
import FitnessBro.web.dto.Member.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService{

    @Value("${jwt.secret}")
    private String key;
    private Long expireTimeMs = 1000 *60 * 60l;
    private final BCryptPasswordEncoder encoder;

    private final MemberRepository memberRepository;
    private final CoachRepository coachRepository;
    private final FavoriteRepository favoriteRepository;

    @Override
    public Member getMemberById(Long memberId){
        Member member = memberRepository.getById(memberId);
        return member;
    }

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

    // social member email, id 값을 각각 email, password에 저장 한 후 토큰 발급
    @Override
    @Transactional
    public String joinSocialMember(String email, String id) {
        String token = JwtTokenUtil.createToken(email, key,expireTimeMs);

        if (memberRepository.existsByEmail(email)){
            return token;
        }

        Member member = Member.builder()
                .email(email)
                .password(id)
                .build();

        memberRepository.save(member);

        return token;
    }

    @Override
    @Transactional
    public void createFavoriteCoach(Long userId, Long coachId) {

        // userId, coachId로 멤버와 코치 객체 가져오기
        Member member = memberRepository.findById(userId).orElse(null);
        Coach coach = coachRepository.findById(coachId).orElse(null);

        // favorites repository에 저장
        Favorites favorites = FavoriteConverter.toFavorite(member, coach);
        favoriteRepository.save(favorites);
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


    @Override
    @Transactional
    public Member updateMember(Long memberId, MemberRequestDTO.MemberUpdateRequestDTO memberUpdateRequestDTO){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        member.update(memberUpdateRequestDTO);
        memberRepository.save(member);
        return member;
    }

    @Override
    @Transactional
    public String classifyUsers(String Email, Role role){
        System.out.println("시작");

        String userEmail = Email;
        System.out.println(userEmail);
        if (role.equals(Role.COACH)) {
            Optional<Member> member = memberRepository.findByEmail(userEmail);
            Coach coach = Coach.builder()
                    .email(userEmail)
                    .build();

            coachRepository.save(coach);
            memberRepository.deleteMemberByEmail(userEmail);
        }


        return "SUCCESS";
    }

    @Override
    @Transactional
    public Optional<Member> insertInfo(Long memberId, MemberRequestDTO.MemberProfileRegisterDTO request){
        Optional<Member> member = memberRepository.findById(memberId);

        member.ifPresent(t-> {

            t.setNickname(request.getNickname());

            t.setAge(request.getAge());

            memberRepository.save(t);
        });

        return member;
    }


}
