package lotto.util;

import java.util.Arrays;
import java.util.List;
import lotto.constant.ErrorMessage;

public class InputParser {
    private static final String ONLY_NUMBER_PATTERN = "^[0-9]+$";
    private static final String VALID_AMOUNT_PATTERN = "^[0-9]{1,3}(,[0-9]{3})*$";

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
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
        }
    }

    public static int parseAmount(String input) {
        String stripInput = input.strip();
        validateAmountFormat(stripInput);
        return parseInteger(stripInput.replace(",", ""));
    }

    private static void validateAmountFormat(String input) {
        boolean isValidCommaFormat = input.matches(VALID_AMOUNT_PATTERN);
        boolean isOnlyNumber = input.matches(ONLY_NUMBER_PATTERN);

        if (!(isValidCommaFormat || isOnlyNumber)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
        }
    }

    public static void validateNullOrEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NULL_OR_EMPTY_INPUT.getMessage());
        }
    }

    private static List<String> splitInputNumbers(String input) {
        String[] inputNumbers = input.split(",", -1);
        return Arrays.stream(inputNumbers).toList();
    }
}
