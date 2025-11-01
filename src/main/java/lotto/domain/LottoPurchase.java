package lotto.domain;

public class LottoPurchase {
    private static final int LOTTO_PRICE = 1000;
    private static final int MAX_LOTTO_PURCHASE = 100_000;

    private final int amount;
    private final int lottoCount;

    public LottoPurchase(String input) {
        int amount = parseAmount(input);
        validatePurchaseAmount(amount);
        this.amount = amount;
        this.lottoCount = calculateLottoCount(amount);
    }

    private int parseAmount(String input) {
        try {
            return Integer.parseInt(input.strip().replace(",", ""));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자만 입력 가능합니다.");
        }
    }

    private void validatePurchaseAmount(int amount) {
        if (amount % LOTTO_PRICE != 0 || amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("1,000원 단위로 입력해 주세요.");
        }

        if (amount > MAX_LOTTO_PURCHASE) {
            throw new IllegalArgumentException("한 번에 100,000원까지 구매 가능합니다.");
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
