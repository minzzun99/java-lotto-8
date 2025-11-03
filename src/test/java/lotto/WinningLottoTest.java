package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.constant.ErrorMessage;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningLottoTest {
    @Nested
    @DisplayName("당첨 등수 확인 테스트")
    class CheckRankTest {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, 7);

        @Test
        @DisplayName("6개 일치 - 1등")
        void 여섯개_일치_1등() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            assertThat(winningLotto.checkRank(lotto)).isEqualTo(Rank.FIRST);
        }

        @Test
        @DisplayName("5개 일치 + 보너스 번호 일치 - 2등")
        void 다섯개_일치_보너스_번호_일치_2등() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
            assertThat(winningLotto.checkRank(lotto)).isEqualTo(Rank.SECOND);
        }

        @Test
        @DisplayName("5개 일치 + 보너스 번호 불일치 - 3등")
        void 다섯개_일치_보너스_번호_불일치_3등() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));
            assertThat(winningLotto.checkRank(lotto)).isEqualTo(Rank.THIRD);
        }

        @Test
        @DisplayName("4개 일치 - 4등")
        void 네개_일치_4등() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));
            assertThat(winningLotto.checkRank(lotto)).isEqualTo(Rank.FOURTH);
        }

        @Test
        @DisplayName("3개 일치 - 5등")
        void 세개_일치_5등() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));
            assertThat(winningLotto.checkRank(lotto)).isEqualTo(Rank.FIFTH);
        }

        @Test
        @DisplayName("2개 이하 일치 - 낙첨")
        void 두개_이하_일치_낙첨() {
            Lotto lotto = new Lotto(List.of(1, 2, 8, 9, 10, 11));
            assertThat(winningLotto.checkRank(lotto)).isEqualTo(Rank.NONE);
        }
    }

    @Nested
    @DisplayName("보너스 번호 검증 테스트")
    class validateBonusNumberTest {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        @ParameterizedTest
        @DisplayName("보너스 번호 범위 초과 예외 발생 테스트")
        @ValueSource(ints = {-1, 0, 46})
        void 보너스_번호_범위_초과_예외_발생(int bonusNumber) {
            assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE.getMessage());
        }

        @Test
        @DisplayName("당첨 번호와 중복된 보너스 번호 예외 발생 테스트")
        void 보너스_번호_중복_예외_발생() {
            assertThatThrownBy(() -> new WinningLotto(winningNumbers, 6))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
        }

        @Test
        @DisplayName("유효한 보너스 번호 정상 동작 확인 테스트")
        void 유효한_보너스_번호_정상_동작() {
            assertThatCode(() -> new WinningLotto(winningNumbers, 7))
                    .doesNotThrowAnyException();
        }
    }
}
