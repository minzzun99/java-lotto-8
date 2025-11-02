package lotto.domain;

import lotto.constant.ErrorMessage;
import lotto.constant.LottoConstant;
import lotto.util.InputParser;

public class LottoPurchase {
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
        if (amount % LottoConstant.PRICE_PER_LOTTO != 0 || amount < LottoConstant.PRICE_PER_LOTTO) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_AMOUNT_UNIT.getMessage());
        }
    }

    private void validateMaxAmount(int amount) {
        if (amount > LottoConstant.MAX_LOTTO_PURCHASE) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_OVER_LIMIT.getMessage());
        }
    }

    private int calculateLottoCount(int amount) {
        return amount / LottoConstant.PRICE_PER_LOTTO;
    }

    public int getAmount() {
        return amount;
    }

    public int getLottoCount() {
        return lottoCount;
    }
}
