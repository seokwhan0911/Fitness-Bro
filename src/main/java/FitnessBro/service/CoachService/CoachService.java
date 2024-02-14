package FitnessBro.service.CoachService;


import FitnessBro.domain.Coach;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface CoachService {

    Coach getCoachById(Long coachId);

    public List<Coach> getCoachList();

    public void insertCoachInfo(Long coachId, CoachRequestDTO.CoachProfileRegisterDTO request);

    Coach updateCoach(Long coachId, CoachRequestDTO.CoachUpdateRequestDTO coachUpdateRequestDTO);
    void insertCoachPicture(Long coachId, MultipartFile picture);

    void insertCoachAlbum(Long coachId, List<MultipartFile> pictureList);

    void deleteCoachPictures(Long userId);



}
