package lotto.domain;

import lotto.constant.ErrorMessage;

public class WinningLotto {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int BONUS_NUMBER_CHECK_MATCH_COUNT = 5;

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
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE.getMessage());
        }
    }

    private void validateBonusNumberDuplicate(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }

    public Rank checkRank(Lotto lotto) {
        int matchCount = countMatchNumbers(lotto);
        boolean isBonusMatch = checkBonusNumberMatch(lotto, matchCount);
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

    private boolean checkBonusNumberMatch(Lotto lotto, int matchCount) {
        if (matchCount != BONUS_NUMBER_CHECK_MATCH_COUNT) {
            return false;
        }
        return lotto.getNumbers().contains(bonusNumber);
    }
}
