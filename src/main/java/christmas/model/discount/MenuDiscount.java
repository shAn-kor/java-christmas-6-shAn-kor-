package christmas.model.discount;

import static christmas.constants.AnniversaryDays.WEEKEND;
import static christmas.constants.ShowingMessage.DESSERT;
import static christmas.constants.ShowingMessage.MAIN;
import static christmas.constants.ShowingMessage.NONE_DISCOUNT;

public class MenuDiscount {
    private final Integer day;

    private MenuDiscount(Integer day) {
        this.day = day;
    }

    public static MenuDiscount of (Integer day) {
        return new MenuDiscount(day);
    }

    public String calculateDiscount(String menuType) {
        if ((isWeekend() && menuType.equals(MAIN.getMessage()))
                || (!isWeekend() && menuType.equals(DESSERT.getMessage()))) {
            return menuType;
        }

        return NONE_DISCOUNT.getMessage();
    }

    private Boolean isWeekend() {
        return WEEKEND.getDays().contains(day);
    }
}
