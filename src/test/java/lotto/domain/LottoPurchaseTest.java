package lotto.domain;

import lotto.constant.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoPurchaseTest {
    @Nested
    @DisplayName("예외 발생 테스트")
    class ExceptionTest {
        @Test
        @DisplayName("1,000원 단위가 아닌 금액 예외 발생 테스트")
        void 천원_단위가_아닌_금액_예외_발생() {
            assertThatThrownBy(() -> new LottoPurchase("1500"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_AMOUNT_UNIT.getMessage());
        }

        @Test
        @DisplayName("100,000원 초과 금액 입력 예외 발생 테스트")
        void 십만원_초과_입력_예외_발생() {
            assertThatThrownBy(() -> new LottoPurchase("101000"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.PURCHASE_AMOUNT_OVER_LIMIT.getMessage());
        }

        @Test
        @DisplayName("1,000원 미만 금액 입력 예외 발생 테스트")
        void 천원_미만_금액_입력_예외_발생() {
            assertThatThrownBy(() -> new LottoPurchase("900"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_AMOUNT_UNIT.getMessage());
        }

        @ParameterizedTest
        @DisplayName("10,00원과 같은 유효하지 않은 쉼표 형식 예외 발생 테스트")
        @ValueSource(strings = {"10,00", "100,0", "100,00"})
        void 유효하지_않은_쉼표_형식_예외_발생(String input) {
            assertThatThrownBy(() -> new LottoPurchase(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
        }

        @ParameterizedTest
        @DisplayName("문자 입력 예외 발생 테스트")
        @ValueSource(strings = {"천원", "1000원", "abc", "a"})
        void 문자_입력_예외_발생(String input) {
            assertThatThrownBy(() -> new LottoPurchase(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
        }

        @Test
        @DisplayName("NULL 값 입력 예외 발생 테스트")
        void NULL_값_입력_예외_발생() {
            assertThatThrownBy(() -> new LottoPurchase(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.NULL_OR_EMPTY_INPUT.getMessage());
        }

        @ParameterizedTest
        @DisplayName("빈 문자열 공백 문자 입력 예외 발생 테스트")
        @ValueSource(strings = {"    ", " ", "", "\s", "\n"})
        void 빈_문자열_공백_문자_입력_예외_발생(String input) {
            assertThatThrownBy(() -> new LottoPurchase(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.NULL_OR_EMPTY_INPUT.getMessage());
        }
    }

    @Nested
    @DisplayName("정상 동작 테스트")
    class NormalTest {
        @ParameterizedTest
        @DisplayName("로또 개수 계산 테스트")
        @CsvSource({
                "'1000', 1000, 1",
                "'5000', 5000, 5",
                "'10000', 10000, 10",
                "'100000', 100000, 100"
        })
        void 로또_개수_계산(String input, int amount, int count) {
            LottoPurchase lottoPurchase = new LottoPurchase(input);

            assertThat(lottoPurchase.getAmount()).isEqualTo(amount);
            assertThat(lottoPurchase.getLottoCount()).isEqualTo(count);
        }

        @ParameterizedTest
        @DisplayName("쉼표 포함 금액 로또 개수 계산 테스트")
        @CsvSource({
                "'1,000', 1000, 1",
                "'10,000', 10000, 10",
                "'100,000', 100000, 100"
        })
        void 쉼표_포함_입력_로또_개수_계산(String input, int amount, int count) {
            LottoPurchase lottoPurchase = new LottoPurchase(input);

            assertThat(lottoPurchase.getAmount()).isEqualTo(amount);
            assertThat(lottoPurchase.getLottoCount()).isEqualTo(count);
        }
    }
}
