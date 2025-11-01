package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.dto.WinningResultDto;
import lotto.service.LottoService;
import lotto.util.InputParser;
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
        try {
            String inputAmount = InputView.requestPurchaseAmount();
            return new LottoPurchase(inputAmount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return inputPurchaseAmount();
    }

    private void printPurchaseLottos(Lottos lottos) {
        OutputView.printPurchaseCount(lottos.getSize());
        OutputView.printLottos(lottoService.convertLottosToString(lottos));
    }

    private WinningLotto inputWinningLotto() {
        try {
            String inputWinningNumbers = InputView.requestWinningNumbers();
            Lotto winningNumbers = parseWinningNumbers(inputWinningNumbers);

            String inputBonusNumber = InputView.requestBonusNumber();
            int bonusNumber = parseBonusNumber(inputBonusNumber);

            return new WinningLotto(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return inputWinningLotto();
    }

    private Lotto parseWinningNumbers(String input) {
        try {
            return new Lotto(InputParser.parseToIntegerList(input));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return parseWinningNumbers(input);
    }

    private int parseBonusNumber(String input) {
        try {
            return InputParser.parseInteger(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return parseBonusNumber(input);
    }

    private void printWinningResult(WinningResultDto winningResultDto) {
        OutputView.printResult(winningResultDto);
    }
}
