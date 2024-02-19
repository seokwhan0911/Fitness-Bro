package FitnessBro.service.MemberService;

import FitnessBro.apiPayload.Utill.JwtTokenUtil;
import FitnessBro.apiPayload.code.status.ErrorStatus;
import FitnessBro.apiPayload.exception.AppException;
import FitnessBro.apiPayload.exception.handler.TempHandler;
import FitnessBro.aws.s3.AmazonS3Manager;
import FitnessBro.converter.FavoriteConverter;
import FitnessBro.converter.MemberConverter;
import FitnessBro.domain.Coach;
import FitnessBro.domain.Favorites;
import FitnessBro.domain.Member;
import FitnessBro.domain.common.Uuid;
import FitnessBro.respository.CoachRepository;
import FitnessBro.respository.FavoriteRepository;
import FitnessBro.respository.MemberRepository;
import FitnessBro.respository.UuidRepository;
import FitnessBro.web.dto.Login.Role;
import FitnessBro.web.dto.Member.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    @Value("${jwt.secret}")
    private String key;

    private Long expireTimeMs = 100000 *60 * 60l;
    private final BCryptPasswordEncoder encoder;

    private final MemberRepository memberRepository;
    private final CoachRepository coachRepository;
    private final FavoriteRepository favoriteRepository;
    private final UuidRepository uuidRepository;
    private final AmazonS3Manager s3Manager;

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
        String token = JwtTokenUtil.createToken(email,key,expireTimeMs);

        if (memberRepository.existsByEmail(email) || coachRepository.existsByEmail(email)){
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

        if(coach == null ){
            throw new TempHandler(ErrorStatus.COACH_NOT_FOUND);
        }

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
    public void insertMemberInfo(Long memberId, MemberRequestDTO.MemberProfileRegisterDTO request){

        if(request.getNickname() == null){
            throw new TempHandler(ErrorStatus.NICKNAME_NOT_EXIST);
        }
        if (request.getAddress()==null){
            throw new TempHandler(ErrorStatus.ADDRESS_NOT_EXIST);
        }

        Member member = memberRepository.findById(memberId).orElse(null);
        member.setNickname(request.getNickname());
        member.setAddress(request.getAddress());

    }

    @Override
    @Transactional
    public void insertInfoWithImage(Long memberId, MemberRequestDTO.MemberProfileRegisterDTO request, MultipartFile file) {

        Member member = memberRepository.findById(memberId).orElse(null);
        member.setNickname(request.getNickname());
        member.setAddress(request.getAddress());

        if(member.getPictureURL() != null){      // 이미 프로필 이미지가 존재하는 경우 AmazonS3에서 지우는 코드
            String memberPictureURL = member.getPictureURL();
            String savedUuid = memberPictureURL.substring(memberPictureURL.lastIndexOf("/profile/") + "/profile/".length());
            Uuid uuid = uuidRepository.findByUuid(savedUuid);

            s3Manager.deleteFile(s3Manager.generateProfileKeyName(uuid));
            uuidRepository.deleteByUuid(savedUuid);
            member.setPictureURL(null);
        }

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());
        String pictureUrl = s3Manager.uploadFile(s3Manager.generateProfileKeyName(savedUuid), file);
        member.setPictureURL(pictureUrl);

    }

    @Override
    public void deleteMemberPicture(Long userId) {

        Member member = memberRepository.findById(userId).orElse(null);

        if(member.getPictureURL() != null){      // 이미 프로필 이미지가 존재하는 경우 AmazonS3에서 지우는 코드
            String memberPictureURL = member.getPictureURL();
            String savedUuid = memberPictureURL.substring(memberPictureURL.lastIndexOf("/profile/") + "/profile/".length());
            Uuid uuid = uuidRepository.findByUuid(savedUuid);

            s3Manager.deleteFile(s3Manager.generateProfileKeyName(uuid));
            uuidRepository.deleteByUuid(savedUuid);
            member.setPictureURL(null);
        }
    }

}
