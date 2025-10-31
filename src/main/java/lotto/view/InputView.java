package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String requestPurchaseAmount() {
        System.out.println(PrintMessage.INPUT_PURCHASE_AMOUNT.getMessage());
        return Console.readLine();
    }

    public static String requestWinningNumbers() {
        System.out.println(PrintMessage.INPUT_WINNING_NUMBERS.getMessage());
        return Console.readLine();
    }

    public static String requestBonusNumber() {
        System.out.println(PrintMessage.INPUT_BONUS_NUMBER.getMessage());
        return Console.readLine();
    }
}
