package FitnessBro.converter;

import FitnessBro.domain.Coach;
import FitnessBro.domain.CoachImage;
import FitnessBro.domain.ReviewImage;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.web.dto.Coach.CoachResponseDTO;
import java.util.List;
import java.util.stream.Collectors;

public class CoachConverter {


    public static CoachResponseDTO.CoachProfileDTO toCoachProfileDTO(Coach coach){
        return CoachResponseDTO.CoachProfileDTO.builder()
                .coachId(coach.getId())
                .nickname(coach.getNickname())
                .age(coach.getAge())
                .rating(coach.getRating())
                .comment(coach.getComment())
                .introduction(coach.getIntroduction())
                .price(coach.getPrice())
                .address(coach.getAddress())
                .schedule(coach.getSchedule())
                .coachPicture(coach.getPictureURL())
                .build();
    }

    public static CoachResponseDTO.CoachDTO toCoachDTO(Coach coach){
        return CoachResponseDTO.CoachDTO.builder()
                .coachId(coach.getId())
                .name(coach.getNickname())
                .age(coach.getAge())
                .rating(coach.getRating())
                .comment(coach.getComment())
                .price(coach.getPrice())
                .address(coach.getAddress())
                .build();
    }

    public static List<CoachResponseDTO.CoachListDTO> toCoachListDTO(List<Coach> coaches) {

            List<CoachResponseDTO.CoachListDTO> coachList = coaches.stream()
                                        .map(coach -> CoachResponseDTO.CoachListDTO.builder()
                                                .coachId(coach.getId())
                                                .nickname(coach.getNickname())
                                                .region(coach.getRegion())
                                                .subAddress(coach.getSubAddress())
                                                .detailAddress(coach.getDetailAddress())
                                                .age(coach.getAge())
                                                .rating(coach.getRating())
                                                .build()).collect(Collectors.toList());

            return coachList;
    }

    public static CoachResponseDTO.CoachMyPageDTO toCoachMyPageDTO(Coach coach, Long matchNum, Long reviewNum){
        return CoachResponseDTO.CoachMyPageDTO.builder()
                .coachImage(coach.getPictureURL())
                .nickname(coach.getNickname())
                .matchNum(matchNum)
                .reviewNum(reviewNum)
                .build();
    }
    public static CoachResponseDTO.favoriteCoachDTO toFavoriteCoachDTO(Coach coach){
        // Coach 엔티티를 FavoriteCoachDTO로 변환
        return  CoachResponseDTO.favoriteCoachDTO.builder()
                .coachId(coach.getId())
                .nickname(coach.getNickname())
                .coachImage(coach.getPictureURL())
                .address(coach.getAddress())
                .rating(coach.getRating())
                .build();
    }

    public static CoachResponseDTO.CoachUpdateResponseDTO toCoachUpdateDTO(Coach coach) {
        return CoachResponseDTO.CoachUpdateResponseDTO.builder()
                .nickname(coach.getNickname())
                .password(coach.getPassword())
                .address(coach.getAddress())
                .comment(coach.getComment())
                .price(coach.getPrice())
                .schedule(coach.getSchedule())
                .introduction(coach.getIntroduction())
                .build();
    }


    public static CoachImage toCoachAlbum(String pictureUrl, Coach coach) {
        return CoachImage.builder()
                .url(pictureUrl)
                .coach(coach)
                .build();
    }

    public static CoachResponseDTO.CoachAlbumDTO toCoachAlbumDTO(Coach coach) {
        return CoachResponseDTO.CoachAlbumDTO.builder()
                .coachId(coach.getId())
                .pictureURLs(coach.getCoachImageList().stream().
                        map(CoachImage::getUrl).
                        collect(Collectors.toList()))
                .build();
    }

    public static CoachResponseDTO.CoachMyInfoDTO toCoachMyInfoDTO(Coach coach) {
        return CoachResponseDTO.CoachMyInfoDTO.builder()
                .coachPicture(coach.getPictureURL())
                .nickname(coach.getNickname())
                .price(coach.getPrice())
                .age(coach.getAge())
                .schedule(coach.getSchedule())
                .comment(coach.getComment())
                .introduction(coach.getIntroduction())
                .pictureURLs(coach.getCoachImageList().stream().
                        map(CoachImage::getUrl).
                        collect(Collectors.toList()))
                .build();
    }
}
