package lotto.domain;

import lotto.constant.ErrorMessage;
import lotto.util.InputParser;

public class LottoPurchase {
    private static final int LOTTO_PRICE = 1000;
    private static final int MAX_LOTTO_PURCHASE = 100_000;

    private final int amount;
    private final int lottoCount;

    public LottoPurchase(String input) {
        int amount = InputParser.parseAmount(input);
        validatePurchaseAmount(amount);
        this.amount = amount;
        this.lottoCount = calculateLottoCount(amount);
    }

    private void validatePurchaseAmount(int amount) {
        validateAmountUnit(amount);
        validateMaxAmount(amount);
    }

    private void validateAmountUnit(int amount) {
        if (amount % LOTTO_PRICE != 0 || amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_AMOUNT_UNIT.getMessage());
        }
    }

    private void validateMaxAmount(int amount) {
        if (amount > MAX_LOTTO_PURCHASE) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_OVER_LIMIT.getMessage());
        }
    }

    private int calculateLottoCount(int amount) {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }

    public int getLottoCount() {
        return lottoCount;
    }
}
