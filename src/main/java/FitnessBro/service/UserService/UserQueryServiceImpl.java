package FitnessBro.service.UserService;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.favorites.Entity.Favorites;
import FitnessBro.domain.user.Entity.Member;
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
public class UserQueryServiceImpl implements UserQueryService {

    private final MemberRepository memberRepository;
    private final CoachRepository coachRepository;
    private final FavoriteRepository favoriteRepository;

    @Override
    public List<Coach> getFavoriteCoachList(Long memberId) {

        Member member = memberRepository.findById(memberId).get();

        List<Favorites> favoritesList = favoriteRepository.findAllByMember(member);

        List<Coach> coachList = favoritesList.stream()
                .map(Favorites::getCoach)
                .collect(Collectors.toList());

        return coachList;
    }
}
