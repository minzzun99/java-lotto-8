package lotto.dto;

import java.util.Collections;
import java.util.List;

public class WinningResultDto {
    private final List<RankResult> winningResults;
    private final double totalReturnRate;

    public WinningResultDto(List<RankResult> winningResults, double totalReturnRate) {
        this.winningResults = winningResults;
        this.totalReturnRate = totalReturnRate;
    }

    public List<RankResult> getWinningResults() {
        return Collections.unmodifiableList(winningResults);
    }

    public double getTotalReturnRate() {
        return totalReturnRate;
    }
}
