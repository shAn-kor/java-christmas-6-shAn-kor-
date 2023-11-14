package christmas.model.menu;

import static christmas.constants.ShowingMessage.APPETIZER;
import static christmas.constants.ShowingMessage.DESSERT;
import static christmas.constants.ShowingMessage.DRINK;
import static christmas.constants.ShowingMessage.MAIN;

public enum RestaurantMenu {
    BUTTON_MUSHROOM_SOUP("양송이수프", 6_000, APPETIZER.getMessage(), 0),
    TAPAS("타파스", 5_500, APPETIZER.getMessage(), 0),
    CAESAR_SALAD("시저샐러드", 8_000, APPETIZER.getMessage(), 0),

    T_BONE_STEAK("티본스테이크", 55_000, MAIN.getMessage(), 0),
    BARBECUE_RIPS("바비큐립", 54_000, MAIN.getMessage(), 0),
    SEA_FOOD_PASTA("해산물파스타", 35_000, MAIN.getMessage(), 0),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MAIN.getMessage(), 0),

    CHOCO_CAKE("초코케이크", 15_000, DESSERT.getMessage(), 0),
    ICE_CREAM("아이스크림", 5_000, DESSERT.getMessage(), 0),

    COKE_ZERO("제로콜라", 3_000, DRINK.getMessage(), 0),
    RED_WINE("레드와인", 60_000, DRINK.getMessage(), 0),
    CHAMPAGNE("샴페인", 25_000, DRINK.getMessage(), 0);

    private final String type;
    private final String foodName;
    private final Integer foodPrice;
    private Integer count;

    RestaurantMenu(String foodName, Integer foodPrice, String type, Integer count) {
        this.foodName = foodName;
        this.type = type;
        this.foodPrice = foodPrice;
        this.count = count;
    }

    public String getName() {
        return foodName;
    }

    public Integer getPrice() {
        return foodPrice;
    }

    public String getType() {
        return type;
    }

    public Integer getCount() {
        return count;
    }

    public void orderFood(Integer count) {
        this.count = count;
    }
}
