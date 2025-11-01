package lotto.domain.strategy;

import java.util.List;

@FunctionalInterface
public interface NumberGenerator {
    List<Integer> generate();
}
