package christmas.util;

import static christmas.util.Parser.parseMenus;
import static christmas.util.Parser.parseNameCount;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;

public class Convertor {
    public static Integer convertStringToInteger (String input) {
        return Integer.parseInt(input);
    }

    public static Map<String, String> convertMenus (String input) {
        return parseNameCount(parseMenus(input));
    }

    public static Map<String, Integer> convertMapValues (Map<String, String> menus) {
        return menus.entrySet().stream()
                .collect(toMap(
                                Map.Entry::getKey,
                                entry -> convertStringToInteger(entry.getValue())
                )
        );
    }

    public static List<Integer> convertListStringToInteger (List<String> input) {
        return input.stream().map(Convertor::convertStringToInteger).toList();
    }
}
