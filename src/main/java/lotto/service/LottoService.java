package lotto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.domain.strategy.NumberGenerator;
import lotto.dto.RankResult;
import lotto.dto.WinningResultDto;
import lotto.util.InputParser;

public class LottoService {
    private final NumberGenerator numberGenerator;

    public LottoService(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Lottos generateLottos(LottoPurchase lottoPurchase) {
        List<Lotto> lottos = new ArrayList<>();
        int count = lottoPurchase.getLottoCount();
        for (int i = 0; i < count; i++) {
            List<Integer> numbers = numberGenerator.generate();
            lottos.add(new Lotto(numbers));
        }
        return new Lottos(lottos);
    }

    public List<String> convertLottosToString(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(this::formatLottoNumbers)
                .toList();
    }

    private String formatLottoNumbers(Lotto lotto) {
        List<String> numbers = lotto.getNumbers().stream()
                .map(String::valueOf)
                .toList();
        return String.join(", ", numbers);
    }

    public WinningResultDto calculateWinningResult(Lottos lottos, WinningLotto winningLotto, LottoPurchase lottoPurchase) {
        Map<Rank, Integer> rankCount = lottos.checkRankCount(winningLotto);
        long totalPrize = calculateTotalPrize(rankCount);
        double totalReturnRate = calculateTotalReturnRate(totalPrize, lottoPurchase.getAmount());

        List<RankResult> winningResults = createWinningResults(rankCount);
        return new WinningResultDto(winningResults, totalReturnRate);
    }

    private long calculateTotalPrize(Map<Rank, Integer> rankCount) {
        long totalPrize = 0;
        for (Map.Entry<Rank, Integer> entry : rankCount.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();
            totalPrize += (long) rank.getPrize() * count;
        }
        return totalPrize;
    }

    private double calculateTotalReturnRate(long totalPrize, int purchaseAmount) {
        return (double) totalPrize / purchaseAmount * 100;
    }

    private List<RankResult> createWinningResults(Map<Rank, Integer> rankCount) {
        List<RankResult> winningResults = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            if (rank == Rank.NONE) {
                continue;
            }
            String message = rank.getMessage();
            int prize = rank.getPrize();
            int count = rankCount.getOrDefault(rank, 0);
            winningResults.add(new RankResult(message, prize, count));
        }
        return winningResults;
    }

    public LottoPurchase createLottoPurchase(String input) {
        return new LottoPurchase(input);
    }

    public WinningLotto createWinningLotto(Lotto winningNumbers, int bonusNumber) {
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    public Lotto createLotto(String input) {
        return new Lotto(InputParser.parseToIntegerList(input));
    }

    public int parseBonusNumber(String input) {
        return InputParser.parseInteger(input);
    }
}
