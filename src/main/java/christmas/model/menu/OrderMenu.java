package christmas.model.menu;

import static christmas.constants.ShowingMessage.DRINK;
import static christmas.model.menu.RestaurantMenu.CHAMPAGNE;
import static java.util.Arrays.stream;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class OrderMenu {
    public static void orderFoods(Map<String, Integer> orderedMenus) {
        stream(RestaurantMenu.values())
                .filter(restaurantMenu -> orderedMenus.containsKey(restaurantMenu.getName()))
                .forEach(restaurantMenu -> restaurantMenu.orderFood(orderedMenus.get(restaurantMenu.getName()))
                );
    }

    public static Boolean ifSaleThisFood (String findName) {
        return stream(RestaurantMenu.values())
                .anyMatch(restaurantMenu -> restaurantMenu.getName().equals(findName));
    }

    public static Boolean ifOrderOnlyDrink (List<String> name) {
        return new HashSet<>(getAllDrinkMenu()).containsAll(name);
    }

    public static Integer getTotalOrderAmount() {
        return stream(RestaurantMenu.values())
                .filter(restaurantMenu -> restaurantMenu.getCount() > 0)
                .mapToInt(restaurantMenu -> restaurantMenu.getPrice() * restaurantMenu.getCount())
                .sum();
    }

    public static Integer getMenuCount(String type) {
        return stream(RestaurantMenu.values())
                .filter(restaurantMenu -> restaurantMenu.getType().equals(type))
                .mapToInt(RestaurantMenu::getCount)
                .sum();
    }

    public static Integer getPresentGiftPrice() {
        return CHAMPAGNE.getPrice();
    }

    public static void setCountZero() {
        stream(RestaurantMenu.values()).forEach(restaurantMenu -> restaurantMenu.orderFood(0));
    }

    private static List<String> getAllDrinkMenu() {
        return stream(RestaurantMenu.values())
                .filter(restaurantMenu -> restaurantMenu.getType().equals(DRINK.getMessage()))
                .map(RestaurantMenu::getName)
                .toList();
    }
}
