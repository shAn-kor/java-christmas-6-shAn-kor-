package christmas.model.discount;

import static christmas.constants.AnniversaryDays.SPECIAL_DAY;
import static christmas.constants.PolicyNumbers.SPECIAL_DAY_DISCOUNT_PRICE;

public class SpecialDay {
    private final Integer day;

    private SpecialDay(Integer day) {
        this.day = day;
    }

    public static SpecialDay of (Integer day) {
        return new SpecialDay(day);
    }

    public Integer specialDayDiscount () {
        if (SPECIAL_DAY.getDays().contains(day)) {
            return SPECIAL_DAY_DISCOUNT_PRICE.getNumber();
        }

        return 0;
    }
}
