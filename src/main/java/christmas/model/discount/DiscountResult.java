package christmas.model.discount;

import static christmas.constants.ShowingMessage.MINUS_MONEY_FORMAT;
import static christmas.constants.ShowingMessage.NONE_DISCOUNT;
import static christmas.constants.ShowingMessage.RESULT_PARSER;
import static christmas.model.discount.Discount.GIVEN_PRESENT;
import static christmas.model.discount.Discount.getIfCanDiscount;
import static java.lang.String.format;
import static java.util.Arrays.stream;

import java.util.stream.Collectors;

public class DiscountResult {
    public static Integer totalDiscountResult() {
        if (!getIfCanDiscount()) {
            return 0;
        }

        return stream(Discount.values())
                .filter(discount -> discount.getMoney() > 0)
                .mapToInt(Discount::getMoney)
                .sum();
    }

    public static Integer getExpectedPaymentAmount(Integer orderTotalPrice) {
        if (!getIfCanDiscount()) {
            return 0;
        }

        return orderTotalPrice -
                stream(Discount.values())
                        .filter(discount -> !discount.getMessage().equals(GIVEN_PRESENT.getMessage()))
                        .filter(discount -> discount.getMoney() > 0)
                        .mapToInt(Discount::getMoney)
                        .sum();
    }

    public static String getDiscountResultList() {
        if (!getIfCanDiscount()) {
            return NONE_DISCOUNT.getMessage();
        }

        return stream(Discount.values())
                .filter(discount -> discount.getMoney() > 0)
                .map(discount ->
                        format(
                                "%s%s%s\n",
                                discount.getMessage(),
                                RESULT_PARSER.getMessage(),
                                format(MINUS_MONEY_FORMAT.getMessage(),discount.getMoney())
                        )
                ).collect(Collectors.joining());
    }
}
