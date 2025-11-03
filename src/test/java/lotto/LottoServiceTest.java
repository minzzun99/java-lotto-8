package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.domain.strategy.NumberGenerator;
import lotto.dto.WinningResultDto;
import lotto.service.LottoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {
    private final NumberGenerator testNumberGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
    private final LottoService lottoService = new LottoService(testNumberGenerator);

    @Nested
    @DisplayName("로또 생성 관련 테스트")
    class GenerateLottoTest {
        @Test
        @DisplayName("구입 금액에 따른 로또 생성 개수 확인 테스트")
        void 구입_금액_로또_개수_확인() {
            LottoPurchase lottoPurchase = new LottoPurchase("5000");

            Lottos lottos = lottoService.generateLottos(lottoPurchase);

            assertThat(lottos.getSize()).isEqualTo(5);
        }

        @Test
        @DisplayName("구매한 로또 문자열 반환 테스트")
        void 구매_로또_문자열_반환_확인() {
            Lottos lottos = new Lottos(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6))));

            List<String> purchaseLotto = lottoService.convertLottosToString(lottos);

            assertThat(purchaseLotto).containsExactly("1, 2, 3, 4, 5, 6");
        }
    }

    @Nested
    @DisplayName("당첨 결과 관련 테스트")
    class WinningResultTest {
        @Test
        @DisplayName("총 수익률 계산 테스트")
        void 총_수익률_계산() {
            LottoPurchase purchase = new LottoPurchase("8000");
            WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

            WinningResultDto resultDto = lottoService.calculateWinningResult(purchaseLottos(), winningLotto, purchase);
            assertThat(resultDto.getTotalReturnRate()).isEqualTo(62.5);
        }

        private Lottos purchaseLottos() {
            return new Lottos(List.of(
                    new Lotto(List.of(1, 2, 3, 8, 9, 10)),
                    new Lotto(List.of(8, 9, 10, 11, 12, 13)),
                    new Lotto(List.of(8, 9, 10, 11, 12, 13)),
                    new Lotto(List.of(8, 9, 10, 11, 12, 13)),
                    new Lotto(List.of(8, 9, 10, 11, 12, 13)),
                    new Lotto(List.of(8, 9, 10, 11, 12, 13)),
                    new Lotto(List.of(8, 9, 10, 11, 12, 13)),
                    new Lotto(List.of(8, 9, 10, 11, 12, 13))
            ));
        }
    }

    @Nested
    @DisplayName("객체 생성 및 파싱 테스트")
    class CreateAndParseTest {
        @Test
        @DisplayName("문자열 입력 LottoPurchase 객체 생성 확인 테스트")
        void 문자열_LottoPurchase_객체_생성() {
            LottoPurchase lottoPurchase = lottoService.createLottoPurchase("5000");
            assertThat(lottoPurchase.getLottoCount()).isEqualTo(5);
        }

        @Test
        @DisplayName("WinningLotto 객체 생성 확인 테스트")
        void WinningLotto_객체_생성() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            WinningLotto winningLotto = lottoService.createWinningLotto(lotto, 7);
            assertThat(winningLotto).isNotNull();
        }

        @Test
        @DisplayName("문자열 입력 Lotto 객체 생성 확인 테스트")
        void 문자열_Lotto_객체_생성() {
            Lotto lotto = lottoService.createLotto("1,2,3,4,5,6");
            assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @Test
        @DisplayName("문자열 입력 보너스 번호 파싱 테스트")
        void 문자열_보너스_번호_파싱() {
            int bonusNumber = lottoService.parseBonusNumber("7");
            assertThat(bonusNumber).isEqualTo(7);
        }
    }
}
