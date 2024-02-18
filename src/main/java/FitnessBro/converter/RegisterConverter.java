package FitnessBro.converter;

import FitnessBro.domain.Register;
import FitnessBro.web.dto.RegisterResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RegisterConverter {

    public static RegisterResponseDTO.RegisterMemberDTO toRegisterMemberDTO(Register register){
        return RegisterResponseDTO.RegisterMemberDTO.builder()
                .nickname(register.getMember().getNickname())
                .createdAt(register.getCreatedAt())
                .build();
    }

    public static List<RegisterResponseDTO.RegisterMemberDTO> toRegisterMemberListDTO(List<Register> registerList){
        return registerList.stream()
                .map(register -> toRegisterMemberDTO(register))
                .collect(Collectors.toList());
    }

    public static RegisterResponseDTO.RegisterCoachDTO toRegisterCoachDTO(Register register){
        return RegisterResponseDTO.RegisterCoachDTO.builder()
                .nickname(register.getCoach().getNickname())
                .createdAt(register.getCreatedAt())
                .build();
    }

    public static List<RegisterResponseDTO.RegisterCoachDTO> toRegisterCoachListDTO(List<Register> registerList){
        return registerList.stream()
                .map(register -> toRegisterCoachDTO(register))
                .collect(Collectors.toList());
    }

    public static RegisterResponseDTO.RequestRegisterDTO torequestRegisterDTO(Register register){
        return RegisterResponseDTO.RequestRegisterDTO.builder()
                .memberNickname(register.getMember().getNickname())
                .build();
    }

    public static List<RegisterResponseDTO.RequestRegisterDTO> toRequestRegisterListDTO(List<Register> registerList){
        return registerList.stream()
                .map(register -> torequestRegisterDTO(register))
                .collect(Collectors.toList());
    }
}
