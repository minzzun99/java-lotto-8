package lotto;

import java.util.List;
import lotto.constant.ErrorMessage;
import lotto.util.InputParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputParserTest {
    @Nested
    @DisplayName("예외 발생 테스트")
    class ExceptionTest {
        @Test
        @DisplayName("정수 리스트 변환 NULL 값 입력 예외 발생 테스트")
        void 정수_리스트_변환_NULL_입력_예외_발생() {
            assertThatThrownBy(() -> InputParser.parseToIntegerList(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.NULL_OR_EMPTY_INPUT.getMessage());
        }

        @ParameterizedTest
        @DisplayName("정수 리스트 변환 빈 문자열 입력 예외 발생 테스트")
        @ValueSource(strings = {"    ", " ", "", "\s", "\n"})
        void 정수_리스트_변환_빈_문자열_입력_예외_발생(String input) {
            assertThatThrownBy(() -> InputParser.parseToIntegerList(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.NULL_OR_EMPTY_INPUT.getMessage());
        }

        @Test
        @DisplayName("정수 리스트 변환 숫자가 아닌 입력 예외 발생 테스트")
        void 정수_리스트_변환_숫자_아닌_입력_예외_발생() {
            assertThatThrownBy(() -> InputParser.parseToIntegerList("a,b,c,1,2,3"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
        }

        @Test
        @DisplayName("정수 변환 시 문자 입력 예외 발생 테스트")
        void 정수_변환_문자_입력_예외_발생() {
            assertThatThrownBy(() -> InputParser.parseInteger("lotto"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
        }

        @ParameterizedTest
        @DisplayName("금액 입력 시 유효하지 않은 쉼표 형식 예외 발생 테스트")
        @ValueSource(strings = {"10,00", "100,0", "100,00"})
        void 유효하지_않은_쉼표_형식_예외_발생(String input) {
            assertThatThrownBy(() -> InputParser.parseAmount(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
        }

        @Test
        @DisplayName("금액 입력 시 NULL 입력 예외 발생 테스트")
        void 금액_입력_NULL_입력_예외_발생() {
            assertThatThrownBy(() -> InputParser.parseAmount(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.NULL_OR_EMPTY_INPUT.getMessage());
        }

        @ParameterizedTest
        @DisplayName("금액 입력 시 빈 문자열 입력 예외 발생 테스트")
        @ValueSource(strings = {"    ", " ", "", "\s", "\n"})
        void 금액_입력_빈_문자열_입력_예외_발생(String input) {
            assertThatThrownBy(() -> InputParser.parseAmount(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.NULL_OR_EMPTY_INPUT.getMessage());
        }

        @Test
        @DisplayName("금액 입력 시 문자 포함 입력 예외 발생 테스트")
        void 금액_입력_문자_포함_예외_발생() {
            assertThatThrownBy(() -> InputParser.parseAmount("1000원"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
        }
    }

    @Nested
    @DisplayName("정상 동작 확인 테스트")
    class FunctionTest {
        @Test
        @DisplayName("문자열 정수 리스트 변환 확인 테스트")
        void 문자열_정수_리스트_변환() {
            List<Integer> result = InputParser.parseToIntegerList("1,2,3,4,5,6");
            assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @Test
        @DisplayName("공백 포함 문자열 정수 리스트 변환 확인 테스트")
        void 공백_포함_문자열_정수_리스트_변환() {
            List<Integer> result = InputParser.parseToIntegerList("1 , 2 , 3 , 4 , 5 , 6 ");
            assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @Test
        @DisplayName("문자열 정수 변환 확인 테스트")
        void 문자열_정수_변환() {
            assertThat(InputParser.parseInteger("123")).isEqualTo(123);
        }

        @ParameterizedTest
        @DisplayName("쉼표 형식 금액 입력 정수 변환 테스트")
        @CsvSource({
                "'1,000', 1000",
                "'10,000', 10000",
                "'100,000', 100000"
        })
        void 쉼표_형식_금액_입력_정수_변환(String input, int expectedAmount) {
            assertThat(InputParser.parseAmount(input)).isEqualTo(expectedAmount);
        }
    }
}
