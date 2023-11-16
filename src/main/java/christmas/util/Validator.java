package christmas.util;

import static christmas.constants.PolicyNumbers.MENU_COUNT_LIMIT;
import static christmas.constants.PolicyNumbers.MONTH_FIRST_DAY;
import static christmas.constants.PolicyNumbers.MONTH_LAST_DAY;
import static christmas.constants.ShowingMessage.MENU_PARSER;
import static christmas.constants.ShowingMessage.NAME_COUNT_PARSER;
import static christmas.constants.ShowingMessage.SPACE;
import static christmas.exception.ErrorMessage.COUNT_UNDER_ONE;
import static christmas.exception.ErrorMessage.FAILED_INPUT_DAY;
import static christmas.exception.ErrorMessage.FAILED_ORDER;
import static christmas.exception.ErrorMessage.HAS_SPACE;
import static christmas.exception.ErrorMessage.IS_NOT_DAY;
import static christmas.exception.ErrorMessage.IS_NOT_INTEGER;
import static christmas.exception.ErrorMessage.MENU_SIZE_OVER;
import static christmas.exception.ErrorMessage.NOTHING_PARSER;
import static christmas.exception.ErrorMessage.NOT_FOR_SALE;
import static christmas.exception.ErrorMessage.ONLY_DRINK;
import static christmas.exception.ErrorMessage.PARSER_START_OR_END;
import static christmas.model.menu.OrderMenu.ifOrderOnlyDrink;
import static christmas.model.menu.OrderMenu.ifSaleThisFood;
import static christmas.util.Convertor.convertListStringToInteger;
import static christmas.util.Convertor.convertStringToInteger;

import christmas.exception.PlannerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Validator {

    public static void validateMenu(Map<String, String> menus) {
        menus.forEach((name, count) -> {
            validateCountIsInteger(count);
            validateCountNumber(convertStringToInteger(count));
            validateMenuForSale(name);
        });

        validateMenuOnlyDrink(new ArrayList<>(menus.keySet()));
        validateMenusCount(new ArrayList<>(menus.values()));
    }

    public static String validateInputForm(String input) {
        validateHasSpace(input);
        validateParserStartEnd(input);
        validateNotHasParser(input);

        return input;
    }

    public static void validateDateIsInteger(String input) {
        if (validateIsInteger(input)) {
            throw PlannerException.of(FAILED_INPUT_DAY, IS_NOT_INTEGER);
        }
    }

    public static void validateLegalDay(Integer day) {
        if (day > MONTH_LAST_DAY.getNumber() || day < MONTH_FIRST_DAY.getNumber()) {
            throw PlannerException.of(FAILED_INPUT_DAY, IS_NOT_DAY);
        }
    }

    private static void validateCountIsInteger(String input) {
        if (validateIsInteger(input)) {
            throw PlannerException.of(FAILED_ORDER, IS_NOT_INTEGER);
        }
    }

    private static Boolean validateIsInteger(String input) {
        return !input.chars().allMatch(Character::isDigit);
    }

    private static void validateHasSpace(String input) {
        if (input.contains(SPACE.getMessage())) {
            throw PlannerException.of(FAILED_ORDER, HAS_SPACE);
        }
    }

    private static void validateNotHasParser(String input) {
        boolean ifHasParser = input.contains(NAME_COUNT_PARSER.getMessage());

        if (!ifHasParser) {
            throw PlannerException.of(FAILED_ORDER, NOTHING_PARSER);
        }
    }

    private static void validateParserStartEnd(String input) {
        boolean ifCommaStartEnd =
                input.startsWith(MENU_PARSER.getMessage())
                || input.endsWith(MENU_PARSER.getMessage())
                || input.startsWith(NAME_COUNT_PARSER.getMessage())
                || input.endsWith(NAME_COUNT_PARSER.getMessage());

        if (ifCommaStartEnd) {
            throw PlannerException.of(FAILED_ORDER, PARSER_START_OR_END);
        }
    }

    private static void validateCountNumber (Integer count) {
        if (count < 1) {
            throw PlannerException.of(FAILED_ORDER, COUNT_UNDER_ONE);
        }
    }

    private static void validateMenusCount (List<String> menuName) {
        List<Integer> menus = convertListStringToInteger(menuName);
        if (menus.stream().mapToInt(Integer::intValue).sum() > MENU_COUNT_LIMIT.getNumber()) {
            throw PlannerException.of(FAILED_ORDER, MENU_SIZE_OVER);
        }
    }

    private static void validateMenuForSale (String menuName) {
        if (!ifSaleThisFood(menuName)) {
            throw PlannerException.of(FAILED_ORDER, NOT_FOR_SALE);
        }
    }

    private static void validateMenuOnlyDrink (List<String> menuName) {
        if (ifOrderOnlyDrink(menuName)) {
            throw PlannerException.of(FAILED_ORDER, ONLY_DRINK);
        }
    }
}
