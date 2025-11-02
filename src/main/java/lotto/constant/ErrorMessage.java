package lotto.constant;

public enum ErrorMessage {
    INVALID_AMOUNT_UNIT("구입 금액은 1,000원 단위로만 입력 가능합니다."),
    PURCHASE_AMOUNT_OVER_LIMIT("로또는 한 번에 100,000원까지 구매 가능합니다."),

    INVALID_NUMBER_INPUT("잘못된 입력입니다."),
    NULL_OR_EMPTY_INPUT("값이 입력되지 않았습니다."),

    INVALID_LOTTO_NUMBERS_COUNT("로또 번호는 6개여야 합니다."),
    DUPLICATE_LOTTO_NUMBER("중복된 숫자가 입력됐습니다."),
    INVALID_LOTTO_NUMBER_RANGE("로또 번호는 1~45 사이의 숫자만 가능합니다."),

    ERROR_PREFIX("[ERROR] ");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
