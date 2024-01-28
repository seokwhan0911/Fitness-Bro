package FitnessBro.service.UserService;

import FitnessBro.apiPayload.exception.AppException;
import FitnessBro.apiPayload.exception.ErrorCode;
import FitnessBro.converter.MemberConverter;
import FitnessBro.domain.user.Entity.Member;
import FitnessBro.respository.MemberRepository;
import FitnessBro.web.dto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public String joinMember(MemberRequestDTO.JoinDTO request) {

        // member 중복 체크
        memberRepository.findByMemberId(request.getUserId())
                .ifPresent(member -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, request.getUserId() + "는 이미 있습니다.");
                });

        Member member = MemberConverter.toMember(request);

        member.setPassword(encoder.encode(request.getPassword()));

        memberRepository.save(member);

        return "SUCCESS";
    }
}
