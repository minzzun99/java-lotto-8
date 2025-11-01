package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public int getSize() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public Map<Rank, Integer> checkRankCount(WinningLotto winningLotto) {
        Map<Rank, Integer> rankCount = new HashMap<>();
        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.checkRank(lotto);
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }
        return rankCount;
    }
}
