package lotto.domain;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean isBonusMatch;
    private final int prize;

    Rank(int matchCount, boolean isBonusMatch, int prize) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
        this.prize = prize;
    }

    public static Rank from(int matchCount, boolean isBonusMatch) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5) {
            return getFiveMatchRank(isBonusMatch);
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return NONE;
    }

    private static Rank getFiveMatchRank(boolean isBonusMatch) {
        if (isBonusMatch) {
            return SECOND;
        }
        return THIRD;
    }
}
