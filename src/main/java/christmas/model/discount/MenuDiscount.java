package christmas.model.discount;

import static christmas.constants.AnniversaryDays.WEEKEND;
import static christmas.constants.PolicyNumbers.FOOD_DISCOUNT_PRICE;
import static christmas.constants.ShowingMessage.DESSERT;
import static christmas.constants.ShowingMessage.MAIN;

public class MenuDiscount {
    private final Integer day;

    private MenuDiscount(Integer day) {
        this.day = day;
    }

    public static MenuDiscount of (Integer day) {
        return new MenuDiscount(day);
    }

    public Integer calculateDiscount(String menuType, Integer count) {
        if (this.isWeekend() && menuType.equals(MAIN.getMessage())){
            return count * FOOD_DISCOUNT_PRICE.getNumber();
        }

        if (!this.isWeekend() && menuType.equals(DESSERT.getMessage())){
            return count * FOOD_DISCOUNT_PRICE.getNumber();
        }

        return 0;
    }

    private Boolean isWeekend() {
        return WEEKEND.getDays().contains(day);
    }
}
