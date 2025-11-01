package lotto.domain;

public class WinningLotto {
    private final static int MIN_LOTTO_NUMBER = 1;
    private final static int MAX_LOTTO_NUMBER = 45;

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(Lotto winningLotto, int bonusNumber) {
        validateBonusNumber(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto winningLotto, int bonusNumber) {
        validateBonusNumberRange(bonusNumber);
        validateBonusNumberDuplicate(winningLotto, bonusNumber);
    }

    private void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < MIN_LOTTO_NUMBER || bonusNumber > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이의 숫자만 가능합니다. 다시 입력해주세요.");
        }
    }

    private void validateBonusNumberDuplicate(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("중복된 보너스 번호가 입력되었습니다. 다시 입력해주세요.");
        }
    }

    public Rank checkRank(Lotto lotto) {
        int matchCount = countMatchNumbers(lotto);
        boolean isBonusMatch = lotto.getNumbers().contains(bonusNumber);
        return Rank.from(matchCount, isBonusMatch);
    }

    private int countMatchNumbers(Lotto lotto) {
        int count = 0;
        for (Integer number : lotto.getNumbers()) {
            if (winningLotto.getNumbers().contains(number)) {
                count++;
            }
        }
        return count;
    }
}
