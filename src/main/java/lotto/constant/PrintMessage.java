package lotto.constant;

public enum PrintMessage {
    INPUT_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    INPUT_WINNING_NUMBERS("\n당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBER("\n보너스 번호를 입력해 주세요."),

    PURCHASE_RESULT("%n%d개를 구매했습니다.%n"),
    PURCHASE_LOTTO_NUMBERS("[%s]%n"),

    WINNING_RESULT_HEADER("\n당첨 통계"),
    WINNING_RESULT_SEPARATOR("---"),
    WINNING_RESULT_FORMAT("%s (%,d원) - %d개%n"),
    TOTAL_RETURN("총 수익률은 %.1f%%입니다.");

    private final String message;

    PrintMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
