package FitnessBro.service.RegisterService;

import FitnessBro.domain.Coach;
import FitnessBro.domain.Member;
import FitnessBro.domain.Register;
import FitnessBro.domain.RegisterStatus;
import FitnessBro.respository.CoachRepository;
import FitnessBro.respository.MemberRepository;
import FitnessBro.respository.RegisterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService{

    public final RegisterRepository registerRepository;
    public final CoachRepository coachRepository;
    public final MemberRepository memberRepository;

    @Override
    @Transactional
    public Register getRegisterByMemberCoach(Member member, Coach coach){
        Register register = registerRepository.findByMemberAndCoach(member, coach);
        return register;
    }
    @Override
    @Transactional
    public List<Register> getRegisterListByCoach(Coach coach){
        List<Register> registerList = registerRepository.findAllByCoach(coach);

        return registerList;
    }

    @Override
    @Transactional
    public List<Register> getRegisterListByMember(Member member){
        List<Register> registerList = registerRepository.findAllByMember(member);

        return registerList;
    }

    @Override
    @Transactional
    public List<Register> successRegisterList(List<Register> registerList){

        List<Register> trueRegisterList = new ArrayList<>();

        for (Register register : registerList) {

            if (register.getMemberSuccess() && register.getCoachSuccess()) {
                // 유저와 코치가 모두 '성사' 상태일 때만 리스트에 추가
                trueRegisterList.add(register);
            }
        }

        return trueRegisterList;
    }

    @Override
    @Transactional
    public Register registerSetting(Member member, Coach coach){
        Register existRegister = registerRepository.findByMemberAndCoach(member,coach);
        if(existRegister != null){
            existRegister.setMemberSuccess(true);
            existRegister.setCoachSuccess(false);
            existRegister.setRegisterStatus(RegisterStatus.WAITING);
            return existRegister;
        }
        Register register = Register.builder()
                .member(member)
                .coach(coach)
                .memberSuccess(true)
                .coachSuccess(false)
                .registerStatus(RegisterStatus.WAITING)
                .build();

        registerRepository.save(register);

        return register;
    }

    @Override
    @Transactional
    public Register registerApproveSetting(Member member, Coach coach){

        Register register = getRegisterByMemberCoach(member, coach);
        register.setCoachSuccess(true);
        register.setRegisterStatus(RegisterStatus.APPROVED);
        return register;
    }

    @Override
    @Transactional
    public Register registerRejectSetting(Member member, Coach coach){
        Register register = getRegisterByMemberCoach(member, coach);
        register.setMemberSuccess(false);
        register.setRegisterStatus(RegisterStatus.UNSUCCESS);

        return register;
    }


    @Override
    @Transactional
    public Long getMatchNumCoach(Long coachId){
        return (long)successRegisterList(getRegisterListByCoach(coachRepository.getById(coachId))).size();
    }

    @Override
    @Transactional
    public Long getMatchNumMember(Long memberId){
        return (long)successRegisterList(getRegisterListByMember(memberRepository.getById(memberId))).size();
    }

    @Override
    @Transactional
    public List<Register> getRequestRegisterList(List<Register> registerList){

        List<Register> requestRegisterList = new ArrayList<>();

        for (Register register : registerList) {

            if (register.getMemberSuccess() && !register.getCoachSuccess()) {
                // 유저와 코치가 모두 '성사' 상태일 때만 리스트에 추가
                requestRegisterList.add(register);
            }
        }

        return requestRegisterList;
    }
}
