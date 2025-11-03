package lotto.domain;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    @Test
    @DisplayName("로또 개수 반환 확인 테스트")
    void 로또_개수_반환_확인() {
        List<Lotto> lottoNumbers = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
        );
        Lottos lottos = new Lottos(lottoNumbers);

        assertThat(lottos.getSize()).isEqualTo(2);
    }

    @ParameterizedTest
    @DisplayName("로또 당첨 순위별 개수 계산 테스트")
    @CsvSource({
            "FIRST, 1",
            "SECOND, 1",
            "THIRD, 1",
            "FOURTH, 1",
            "FIFTH, 1",
            "NONE, 2"
    })
    void 로또_당첨_개수_계산(Rank rank, int expectedCount) {
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        List<Lotto> purchaseLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                new Lotto(List.of(1, 2, 3, 4, 8, 9)),
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),
                new Lotto(List.of(1, 2, 8, 9, 10, 11)),
                new Lotto(List.of(1, 8, 9, 10, 11, 12))
        );
        Lottos lottos = new Lottos(purchaseLottos);

        Map<Rank, Integer> rankCount = lottos.checkRankCount(winningLotto);
        assertThat(rankCount.get(rank)).isEqualTo(expectedCount);
    }
}
