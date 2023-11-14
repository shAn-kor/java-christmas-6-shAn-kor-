package christmas.model.menu;

import static christmas.constants.ShowingMessage.DESSERT;
import static christmas.constants.ShowingMessage.DRINK;
import static christmas.constants.ShowingMessage.MAIN;
import static christmas.model.menu.RestaurantMenu.CHAMPAGNE;
import static java.util.Arrays.stream;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class OrderMenu {
    public void orderFoods(Map<String, Integer> orderedMenus) {
        stream(RestaurantMenu.values())
                .filter(restaurantMenu -> orderedMenus.containsKey(restaurantMenu.getName()))
                .forEach(restaurantMenu -> restaurantMenu.orderFood(orderedMenus.get(restaurantMenu.getName()))
                );
    }

    public Boolean ifSaleThisFood (String name) {
        return stream(RestaurantMenu.values())
                .anyMatch(restaurantMenu -> restaurantMenu.getName().equals(name));
    }

    public Boolean ifOrderOnlyDrink (List<String> name) {
        return new HashSet<>(getAllDrinkMenu()).containsAll(name);
    }

    public Integer getTotalOrderAmount() {
        return stream(RestaurantMenu.values())
                .filter(restaurantMenu -> restaurantMenu.getCount() > 0)
                .mapToInt(restaurantMenu -> restaurantMenu.getPrice() * restaurantMenu.getCount())
                .sum();
    }

    public Integer getDessertMenuCount() {
        return getMenuCount(DESSERT.getMessage());
    }

    public Integer getMainMenuCount() {
        return getMenuCount(MAIN.getMessage());
    }

    public Integer getMenuCount(String type) {
        return stream(RestaurantMenu.values())
                .filter(restaurantMenu -> restaurantMenu.getType().equals(type))
                .mapToInt(RestaurantMenu::getCount)
                .sum();
    }

    public Integer getPresentGiftPrice() {
        return CHAMPAGNE.getPrice();
    }

    private List<String> getAllDrinkMenu() {
        return stream(RestaurantMenu.values())
                .filter(restaurantMenu -> restaurantMenu.getType().equals(DRINK.getMessage()))
                .map(RestaurantMenu::getName)
                .toList();
    }
}
