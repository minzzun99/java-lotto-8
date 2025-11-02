package lotto.view;

import java.util.List;
import lotto.constant.PrintMessage;
import lotto.dto.RankResult;
import lotto.dto.WinningResultDto;

public class OutputView {
    public static void printPurchaseCount(int count) {
        System.out.printf((PrintMessage.PURCHASE_RESULT.getMessage()), count);
    }

    public static void printLottos(List<String> lottos) {
        lottos.forEach(lotto ->
                System.out.printf(PrintMessage.PURCHASE_LOTTO_NUMBERS.getMessage(), lotto));
    }

    public static void printResult(WinningResultDto winningResultDto) {
        System.out.println(PrintMessage.WINNING_RESULT_HEADER.getMessage());
        System.out.println(PrintMessage.WINNING_RESULT_SEPARATOR.getMessage());
        printWinningResults(winningResultDto.getWinningResults());
        System.out.printf(PrintMessage.TOTAL_RETURN.getMessage(),winningResultDto.getTotalReturnRate());
    }

    private static void printWinningResults(List<RankResult> winningResults) {
        for (RankResult rankResult : winningResults) {
            System.out.printf(PrintMessage.WINNING_RESULT_FORMAT.getMessage(), rankResult.getMessage(),
                    rankResult.getPrizeMoney(), rankResult.getCount());
        }
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
