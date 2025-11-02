package lotto.domain;

public enum Rank {
    FIFTH(3, false, 5_000L, "3개 일치"),
    FOURTH(4, false, 50_000L, "4개 일치"),
    THIRD(5, false, 1_500_000L, "5개 일치"),
    SECOND(5, true, 30_000_000L, "5개 일치, 보너스 볼 일치"),
    FIRST(6, false, 2_000_000_000L, "6개 일치"),
    NONE(0, false, 0L, "");

    private final int matchCount;
    private final boolean isBonusMatch;
    private final long prize;
    private final String message;

    Rank(int matchCount, boolean isBonusMatch, long prize, String message) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
        this.prize = prize;
        this.message = message;
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

    public long getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }
}
