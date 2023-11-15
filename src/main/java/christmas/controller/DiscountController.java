package christmas.controller;

import static christmas.constants.PolicyNumbers.DISCOUNT_CUT_LINE;
import static christmas.constants.PolicyNumbers.FOOD_DISCOUNT_PRICE;
import static christmas.constants.ShowingMessage.DESSERT;
import static christmas.constants.ShowingMessage.EXPECTED_PAYMENT_AMOUNT;
import static christmas.constants.ShowingMessage.MAIN;
import static christmas.constants.ShowingMessage.MINUS_MONEY_FORMAT;
import static christmas.constants.ShowingMessage.MONEY_FORMAT;
import static christmas.constants.ShowingMessage.NONE_DISCOUNT;
import static christmas.constants.ShowingMessage.SHOW_BADGE;
import static christmas.constants.ShowingMessage.SHOW_BEFORE_DISCOUNT;
import static christmas.constants.ShowingMessage.SHOW_PRESENT;
import static christmas.constants.ShowingMessage.SHOW_TOTAL_DISCOUNT;
import static christmas.constants.ShowingMessage.THE_PRESENT;
import static christmas.model.Badge.getBadge;
import static christmas.model.discount.Discount.DISCOUNT_D_DAY;
import static christmas.model.discount.Discount.DISCOUNT_SPECIAL_DAY;
import static christmas.model.discount.Discount.DISCOUNT_WEEK;
import static christmas.model.discount.Discount.DISCOUNT_WEEKEND;
import static christmas.model.discount.Discount.GIVEN_PRESENT;
import static christmas.model.discount.Discount.noDiscount;
import static christmas.model.discount.DiscountResult.getDiscountResultList;
import static christmas.model.discount.DiscountResult.getExpectedPaymentAmount;
import static christmas.model.discount.DiscountResult.totalDiscountResult;
import static christmas.model.menu.OrderMenu.getMenuCount;
import static christmas.model.menu.OrderMenu.getPresentGiftPrice;
import static christmas.model.menu.OrderMenu.getTotalOrderAmount;
import static christmas.model.menu.RestaurantMenu.CHAMPAGNE;
import static christmas.view.OutputView.printDiscountResult;
import static christmas.view.OutputView.printResult;
import static java.lang.Math.abs;
import static java.lang.String.format;

import christmas.model.discount.DDayDiscount;
import christmas.model.discount.MenuDiscount;
import christmas.model.discount.Presentation;
import christmas.model.discount.SpecialDay;

public class DiscountController {
    public void checkDiscount(Integer day) {
        if (getTotalOrderAmount() < DISCOUNT_CUT_LINE.getNumber()) {
            noDiscount();
            return;
        }

        checkAllDiscount(day);
    }

    public void showResult() {
        Integer totalMoney = getTotalOrderAmount();
        showBeforeDiscount(totalMoney);
        showPresentResult();
        printDiscountResult(getDiscountResultList());
        showTotalDiscountResult();
        showExpectedPaymentAmount(totalMoney);
        showBadge(totalMoney);
    }

    public static void checkAllDiscount(Integer day) {
        DISCOUNT_D_DAY.setMoney(DDayDiscount.of(day).checkDDAyDiscount());
        DISCOUNT_SPECIAL_DAY.setMoney(SpecialDay.of(day).specialDayDiscount());
        DISCOUNT_WEEK.setMoney(setMenuDiscount(day, DESSERT.getMessage()));
        DISCOUNT_WEEKEND.setMoney(setMenuDiscount(day, MAIN.getMessage()));
        GIVEN_PRESENT.setMoney(setPresentResult(getTotalOrderAmount()));
    }

    private void showBeforeDiscount(Integer money) {
        printResult(
                SHOW_BEFORE_DISCOUNT.getMessage(),
                format(MONEY_FORMAT.getMessage(), money)
        );
    }

    private void showPresentResult() {
        if (GIVEN_PRESENT.getMoney() > 0) {
            printResult(SHOW_PRESENT.getMessage(), CHAMPAGNE.getName() + THE_PRESENT.getMessage());
        }

        printResult(SHOW_PRESENT.getMessage(), NONE_DISCOUNT.getMessage());
    }

    private void showTotalDiscountResult() {
        printResult(
                SHOW_TOTAL_DISCOUNT.getMessage(),
                format(MINUS_MONEY_FORMAT.getMessage(), abs(totalDiscountResult()))
        );
    }

    private static Integer setPresentResult(Integer money) {
        if (Presentation.of(money).isPresent()) {
            return getPresentGiftPrice();
        }

        return 0;
    }

    private static Integer setMenuDiscount (Integer day, String type) {
        MenuDiscount menuDiscount = MenuDiscount.of(day);

        if (menuDiscount.calculateDiscount(type).equals(type)) {
            return getMenuCount(type) * FOOD_DISCOUNT_PRICE.getNumber();
        }

        return 0;
    }

    private void showExpectedPaymentAmount(Integer money) {
        printResult(
                EXPECTED_PAYMENT_AMOUNT.getMessage(),
                format(MONEY_FORMAT.getMessage(), getExpectedPaymentAmount(money))
        );
    }

    private void showBadge(Integer money) {
        printResult(SHOW_BADGE.getMessage(), getBadge(money));
    }
}
