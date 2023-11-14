package christmas.util;

import static christmas.constants.ShowingMessage.MENU_PARSER;
import static christmas.constants.ShowingMessage.NAME_COUNT_PARSER;
import static christmas.exception.ErrorMessage.FAILED_ORDER;
import static java.util.stream.Collectors.toMap;

import christmas.exception.PlannerException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Parser {
    public static List<String> parseMenus (String input) {
        return Stream.of(input.split(MENU_PARSER.getMessage())).toList();
    }

    public static List<String> parseEachNameCount (String input) {
        return Stream.of(input.split(NAME_COUNT_PARSER.getMessage())).toList();
    }

    public static Map<String, String> parseNameCount (List<String> menus) {
        return menus.stream()
                .map(Parser::parseEachNameCount)
                .collect(toMap(
                        entry -> entry.get(0),
                        entry -> entry.get(1),
                        (existingValue, newValue) -> {
                            throw PlannerException.of(FAILED_ORDER);
                        }
                ));
    }
}
