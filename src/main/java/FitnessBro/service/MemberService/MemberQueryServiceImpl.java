package FitnessBro.service.MemberService;

import FitnessBro.domain.Coach;
import FitnessBro.domain.Favorites;
import FitnessBro.respository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final FavoriteRepository favoriteRepository;

    @Override
    public List<Coach> getFavoriteCoachList(Long memberId) {


        List<Favorites> favoritesList = favoriteRepository.findAllByMemberId(memberId);

        List<Coach> coachList = favoritesList.stream()
                .map(Favorites::getCoach)
                .collect(Collectors.toList());

        return coachList;
    }
}
