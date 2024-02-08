package FitnessBro.converter;

import FitnessBro.domain.Coach;
import FitnessBro.domain.Favorites;
import FitnessBro.domain.Member;

public class FavoriteConverter {
    public static Favorites toFavorite(Member member, Coach coach) {
        return Favorites.builder()
                .member(member)
                .coach(coach)
                .build();
    }
}
