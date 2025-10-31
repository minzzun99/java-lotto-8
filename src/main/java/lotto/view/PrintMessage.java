package lotto.view;

public enum PrintMessage {
    INPUT_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    INPUT_WINNING_NUMBERS("당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBER("보너스 번호를 입력해 주세요."),

    PURCHASE_RESULT("%d개를 구매했습니다."),
    PURCHASE_LOTTO_NUMBERS("[%s]"),

    WINNING_RESULT_HEADER("당첨 통계"),
    WINNING_RESULT_SEPARATOR("---"),
    WINNING_RESULT_FORMAT("%s (%,d원) - %d개"),
    TOTAL_RETURN("총 수익률은 %.1f%%입니다.");

    private final String message;

    PrintMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
