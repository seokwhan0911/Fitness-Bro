package FitnessBro.service.CoachService;


import FitnessBro.aws.s3.AmazonS3Manager;
import FitnessBro.converter.CoachConverter;
import FitnessBro.converter.ReviewConverter;
import FitnessBro.domain.*;
import FitnessBro.apiPayload.Utill.StringUtils;
import FitnessBro.domain.Coach;
import FitnessBro.domain.common.Uuid;
import FitnessBro.respository.*;

import FitnessBro.service.RegisterService.RegisterService;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import FitnessBro.web.dto.Coach.CoachResponseDTO;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

import static FitnessBro.converter.CoachConverter.toCoachDTO;
import static FitnessBro.converter.CoachConverter.toCoachMyPageDTO;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService{

    private final CoachRepository coachRepository;
    private final CoachImageRepository coachImageRepository;
    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;

    @Override
    @Transactional
    public Coach getCoachById(Long coachId){
        Coach coach = coachRepository.findById(coachId).orElse(null);
        return coach;
    }



    @Override
    @Transactional
    public List<Coach> getCoachList(){

        List<Coach> coaches = coachRepository.findAll();
        return coaches;
    }

    @Override
    @Transactional
    public void insertCoachInfo(Long coachId, CoachRequestDTO.CoachProfileRegisterDTO request){
        Coach coach = coachRepository.findById(coachId).orElse(null);
        coach.setNickname(request.getNickname());
        coach.setIntroduction(request.getIntroduction());   // 선생님 소개
        coach.setSchedule(request.getSchedule());   // 주 운동 시간
        coach.setComment(request.getComment());     // 한 줄 인사말
    }

    @Override
    @Transactional
    public void insertCoachPicture(Long coachId, MultipartFile picture) {   // 동네형 프로필 사진 삽입

        Coach coach = coachRepository.findById(coachId).orElse(null);

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());
        String pictureUrl = s3Manager.uploadFile(s3Manager.generateProfileKeyName(savedUuid), picture);

        coach.setPictureURL(pictureUrl);
    }

    @Override
    @Transactional
    public void insertCoachAlbum(Long coachId, List<MultipartFile> pictureList) {   // 동네형 사진첩 삽입

        Coach coach = coachRepository.findById(coachId).orElse(null);

        // picture마다 유일한 URL 값 생성
        for(MultipartFile picture : pictureList){
            String uuid = UUID.randomUUID().toString();
            Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());

            String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), picture);
            coachImageRepository.save(CoachConverter.toCoachAlbum(pictureUrl, coach));
        }

    }

    @Override
    @Transactional
    public Coach updateCoach(Long coachId, CoachRequestDTO.CoachUpdateRequestDTO coachUpdateRequestDTO){
        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        coach.update(coachUpdateRequestDTO);
        coachRepository.save(coach);
        return coach;
    }




}
