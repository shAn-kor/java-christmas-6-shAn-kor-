package christmas.model.discount;

import static christmas.model.discount.Discount.GIVEN_PRESENT;
import static christmas.model.discount.Discount.getNoneDiscount;
import static java.util.Arrays.stream;

public class DiscountResult {
    public static Integer totalDiscountResult() {
        if (getNoneDiscount()) {
            return 0;
        }

        return stream(Discount.values())
                .filter(discount -> discount.getMoney() > 0)
                .mapToInt(Discount::getMoney)
                .sum();
    }

    public static Integer getExpectedPaymentAmount(Integer orderTotalPrice) {
        if (getNoneDiscount()) {
            return 0;
        }

        return orderTotalPrice -
                stream(Discount.values())
                        .filter(discount -> !discount.getMessage().equals(GIVEN_PRESENT.getMessage()))
                        .filter(discount -> discount.getMoney() > 0)
                        .mapToInt(Discount::getMoney)
                        .sum();
    }
}
