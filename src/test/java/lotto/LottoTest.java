package lotto;

import java.util.stream.Stream;
import lotto.constant.ErrorMessage;
import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Nested
    @DisplayName("로또 번호 생성 및 검증 테스트")
    class LottoValidationTest {
        @Test
        @DisplayName("로또 번호 6개 초과 입력 예외 발생 테스트")
        void 로또_번호의_개수_6개_초과_예외_발생() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_LOTTO_NUMBERS_COUNT.getMessage());
        }

        @Test
        @DisplayName("로또 번호 6개 미만 입력 예외 발생 테스트")
        void 로또_번호_개수_6개_미만_예외_발생() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_LOTTO_NUMBERS_COUNT.getMessage());
        }

        @Test
        @DisplayName("로또 번호 내부 중복된 숫자 포함 시 예외 발생 테스트")
        void 로또_번호_중복된_숫자_예외_발생() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
        }

        @Test
        @DisplayName("로또 번호 범위 초과 예외 발생 테스트")
        void 로또_번호_범위_초과_예외_발생() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE.getMessage());

            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 0)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE.getMessage());
        }

        @Test
        @DisplayName("유효한 로또 번호 정상 동작 확인 테스트")
        void 유효한_로또_번호_정상_동작_확인() {
            assertThatCode(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6)))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("로또 관련 기능 확인 테스트")
    class LottoFunctionTest {
        Lotto lotto = new Lotto(List.of(3, 4, 1, 5, 6, 2));

        @Test
        @DisplayName("로또 번호 오름차순 정렬 확인 테스트")
        void 로또_오름차순_정렬_확인() {
            assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @ParameterizedTest
        @DisplayName("로또 번호 일치 개수 계산 확인 테스트")
        @CsvSource({
                "'1,2,3,4,5,6', 6",
                "'1,2,3,4,5,7', 5",
                "'1,2,3,4,7,8', 4",
                "'1,2,3,7,8,9', 3",
                "'1,2,7,8,9,10', 2",
                "'1,7,8,9,10,11', 1",
                "'7,8,9,10,11,12', 0",
        })
        void 번호_일치_개수_계산_확인(String input, int expectedMatchCount) {
            List<Integer> targetNumbers = Stream.of(input.split(","))
                    .map(Integer::parseInt)
                    .toList();

            int matchCount = lotto.calculateMatchCount(targetNumbers);
            assertThat(matchCount).isEqualTo(expectedMatchCount);
        }
    }
}
