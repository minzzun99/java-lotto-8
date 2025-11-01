package lotto.util;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static List<Integer> parseToIntegerList(String input) {
        validateNullOrEmpty(input);
        return splitInputNumbers(input).stream()
                .map(InputParser::parseInteger)
                .toList();
    }

    public static int parseInteger(String input) {
        try {
            return Integer.parseInt(input.strip());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요.");
        }
    }

    public static void validateNullOrEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요.");
        }
    }

    private static List<String> splitInputNumbers(String input) {
        String[] inputNumbers = input.split(",", -1);
        return Arrays.stream(inputNumbers).toList();
    }
}
