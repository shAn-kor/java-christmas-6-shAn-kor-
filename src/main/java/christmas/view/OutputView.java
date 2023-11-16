package christmas.view;

import static christmas.constants.ShowingMessage.SHOW_ALL_DISCOUNT;
import static christmas.constants.ShowingMessage.SHOW_ALL_ORDER_MENU;
import static christmas.constants.ShowingMessage.SHOW_ORDERED_FORMAT;
import static christmas.constants.ShowingMessage.SHOW_PREVIEW;
import static christmas.constants.ShowingMessage.SHOW_WELCOME;

import christmas.exception.PlannerException;
import java.util.Map;

public class OutputView {
    public static void printStart() {
        System.out.println(SHOW_WELCOME.getMessage());
    }

    public static void printErrorMessageFor(PlannerException exception) {
        System.out.println(exception.getMessage());
    }

    public static void printPreview(Integer day) {
        System.out.printf((SHOW_PREVIEW.getMessage()) + "%n", day);
    }

    public static void printMenu(Map<String, Integer> menus) {
        System.out.println(SHOW_ALL_ORDER_MENU.getMessage());

        for (String name : menus.keySet()) {
            System.out.printf((SHOW_ORDERED_FORMAT.getMessage()) + "%n", name, menus.get(name));
        }
    }

    public static void printResult(String constantMessage, String message) {
        System.out.println(constantMessage);
        System.out.println(message);
    }

    public static void printDiscountResult(String result) {
        System.out.println(SHOW_ALL_DISCOUNT.getMessage());

        System.out.println(result);
    }
}
