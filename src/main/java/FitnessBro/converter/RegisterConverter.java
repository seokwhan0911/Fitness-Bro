package FitnessBro.converter;

import FitnessBro.domain.register.Entity.Register;
import FitnessBro.web.dto.RegisterResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RegisterConverter {

    public static RegisterResponseDTO.RegisterMemberDTO toRegisterMemberDTO(Register register){
        return RegisterResponseDTO.RegisterMemberDTO.builder()
                .nickname(register.getMember().getNickname())
                .creatdAt(register.getCreatedAt())
                .build();
    }

    public static List<RegisterResponseDTO.RegisterMemberDTO> toRegisterMemberListDTO(List<Register> registerList){
        return registerList.stream()
                .map(register -> toRegisterMemberDTO(register))
                .collect(Collectors.toList());
    }


}
