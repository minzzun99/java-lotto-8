package lotto;

import lotto.controller.LottoController;
import lotto.domain.strategy.LottoNumberGenerator;
import lotto.domain.strategy.NumberGenerator;
import lotto.service.LottoService;

public class Application {
    public static void main(String[] args) {
        NumberGenerator numberGenerator = new LottoNumberGenerator();
        LottoService lottoService = new LottoService(numberGenerator);
        LottoController lottoController = new LottoController(lottoService);
        lottoController.run();
    }
}
