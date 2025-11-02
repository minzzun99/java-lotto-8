package lotto.dto;

public class RankResult {
    private final String message;
    private final long prizeMoney;
    private final int count;

    public RankResult(String message, long prizeMoney, int count) {
        this.message = message;
        this.prizeMoney = prizeMoney;
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }

    public int getCount() {
        return count;
    }
}
