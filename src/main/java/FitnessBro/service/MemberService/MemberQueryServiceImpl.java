package FitnessBro.service.MemberService;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.favorites.Entity.Favorites;
import FitnessBro.domain.member.Entity.Member;
import FitnessBro.respository.CoachRepository;
import FitnessBro.respository.FavoriteRepository;
import FitnessBro.respository.MemberRepository;
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
