package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.dto.WinningResultDto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        // 로또 구매 금액 입력 및 로또 발행
        LottoPurchase lottoPurchase = inputPurchaseAmount();
        Lottos lottos = lottoService.generateLottos(lottoPurchase);
        printPurchaseLottos(lottos);
        // 당첨 번호 및 보너스 번호 입력
        WinningLotto winningLotto = inputWinningLotto();
        // 당첨 통계 및 수익률 출력
        WinningResultDto winningResultDto = lottoService.calculateWinningResult(lottos, winningLotto, lottoPurchase);
        printWinningResult(winningResultDto);
    }

    private LottoPurchase inputPurchaseAmount() {
        while (true) {
            try {
                String inputAmount = InputView.requestPurchaseAmount();
                return lottoService.createLottoPurchase(inputAmount);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void printPurchaseLottos(Lottos lottos) {
        OutputView.printPurchaseCount(lottos.getSize());
        OutputView.printLottos(lottoService.convertLottosToString(lottos));
    }

    private WinningLotto inputWinningLotto() {
        Lotto winningNumbers = inputWinningNumbers();
        while (true) {
            try {
                int bonusNumber = inputBonusNumber();
                return lottoService.createWinningLotto(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Lotto inputWinningNumbers() {
        while (true) {
            try {
                String input = InputView.requestWinningNumbers();
                return lottoService.createLotto(input);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int inputBonusNumber() {
        while (true) {
            try {
                String input = InputView.requestBonusNumber();
                return lottoService.parseBonusNumber(input);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void printWinningResult(WinningResultDto winningResultDto) {
        OutputView.printResult(winningResultDto);
    }
}
