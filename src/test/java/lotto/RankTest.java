package lotto;

import lotto.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "3, false, FIFTH"
    })
    @DisplayName("번호 일치 개수와 보너스 번호 일치에 따른 등수 판별 테스트")
    void 일치_개수_보너스_번호_반영_등수_판별(int matchCount, boolean isBonusMatch, Rank expectedRank) {
        Rank rank = Rank.from(matchCount, isBonusMatch);

        assertThat(rank).isEqualTo(expectedRank);
    }

    @ParameterizedTest
    @DisplayName("2개 이하 일치는 낙첨 확인 테스트")
    @CsvSource({
            "0, false",
            "1, false",
            "2, false"
    })
    void 두개_이하_일치_낙첨(int matchCount, boolean isBonusMatch) {
        Rank rank = Rank.from(matchCount, isBonusMatch);

        assertThat(rank).isEqualTo(Rank.NONE);
    }

    @ParameterizedTest
    @CsvSource({
            "3, true, FIFTH",
            "4, true, FOURTH"
    })
    @DisplayName("3개 또는 4개 일치 시 보너스 번호 무시 확인 테스트")
    void 보너스번호_무시(int matchCount, boolean isBonusMatch, Rank expected) {
        assertThat(Rank.from(matchCount, isBonusMatch)).isEqualTo(expected);
    }
}
