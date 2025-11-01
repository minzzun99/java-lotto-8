package lotto.dto;

public class RankResult {
    private final String message;
    private final int prizeMoney;
    private final int count;

    public RankResult(String message, int prizeMoney, int count) {
        this.message = message;
        this.prizeMoney = prizeMoney;
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getCount() {
        return count;
    }
}
