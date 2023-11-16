package christmas.model.discount;

import static christmas.constants.PolicyNumbers.D_Day_DISCOUNT_DAY;
import static christmas.constants.PolicyNumbers.XMAS_D_DAY_DISCOUNT_ADD;
import static christmas.constants.PolicyNumbers.XMAS_D_DAY_DISCOUNT_BASE;

public class DDayDiscount {
    private final Integer day;

    private DDayDiscount(Integer day) {
        this.day = day;
    }

    public static DDayDiscount of (Integer day) {
        return new DDayDiscount(day);
    }

    public Integer checkDDAyDiscount() {
        if (day <= D_Day_DISCOUNT_DAY.getNumber()) {
            return XMAS_D_DAY_DISCOUNT_BASE.getNumber() +
                    XMAS_D_DAY_DISCOUNT_ADD.getNumber() * (day - 1);
        }

        return 0;
    }
}
