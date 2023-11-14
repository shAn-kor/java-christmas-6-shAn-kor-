package christmas.controller;

import static christmas.constants.PolicyNumbers.DISCOUNT_CUT_LINE;
import static christmas.constants.ShowingMessage.DESSERT;
import static christmas.constants.ShowingMessage.EXPECTED_PAYMENT_AMOUNT;
import static christmas.constants.ShowingMessage.MAIN;
import static christmas.constants.ShowingMessage.MINUS_MONEY_FORMAT;
import static christmas.constants.ShowingMessage.MONEY_FORMAT;
import static christmas.constants.ShowingMessage.NONE_DISCOUNT;
import static christmas.constants.ShowingMessage.SHOW_ALL_DISCOUNT;
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
import static christmas.model.discount.Discount.getNoneDiscount;
import static christmas.model.discount.Discount.noDiscount;
import static christmas.model.discount.DiscountResult.getExpectedPaymentAmount;
import static christmas.model.discount.DiscountResult.totalDiscountResult;
import static christmas.view.OutputView.printDiscountResult;
import static christmas.view.OutputView.printResult;
import static java.lang.Math.abs;
import static java.lang.String.format;

import christmas.model.discount.DDayDiscount;
import christmas.model.discount.MenuDiscount;
import christmas.model.discount.Presentation;
import christmas.model.discount.SpecialDay;
import christmas.model.menu.OrderMenu;

public class DiscountController {
    public void checkDiscount(Integer day, OrderMenu orderMenu) {
        if (orderMenu.getTotalOrderAmount() < DISCOUNT_CUT_LINE.getNumber()) {
            noDiscount();
            return;
        }

        checkAllDiscount(day, orderMenu);
    }

    public void showResult(OrderMenu orderMenu) {
        Integer totalMoney = orderMenu.getTotalOrderAmount();
        showBeforeDiscount(totalMoney);

        if (getNoneDiscount()) {
            showNoneDiscountResult(totalMoney);
        }
        showPresentResult();
        printDiscountResult();
        showTotalDiscountResult();
        showExpectedPaymentAmount(totalMoney);
        showBadge(totalMoney);
    }

    public static void checkAllDiscount(Integer day, OrderMenu orderMenu) {
        MenuDiscount menuDiscount = MenuDiscount.of(day);

        DISCOUNT_D_DAY.setMoney(DDayDiscount.of(day).checkDDAyDiscount());
        DISCOUNT_SPECIAL_DAY.setMoney(SpecialDay.of(day).specialDayDiscount());
        DISCOUNT_WEEK.setMoney(menuDiscount.calculateDiscount(DESSERT.getMessage(), orderMenu.getDessertMenuCount()));
        DISCOUNT_WEEKEND.setMoney(menuDiscount.calculateDiscount(MAIN.getMessage(), orderMenu.getMainMenuCount()));
        GIVEN_PRESENT.setMoney(Presentation.of(orderMenu.getTotalOrderAmount()).isPresent());
    }

    private void showNoneDiscountResult(Integer money) {
        printResult(SHOW_PRESENT.getMessage(), NONE_DISCOUNT.getMessage());
        printResult(SHOW_ALL_DISCOUNT.getMessage(), NONE_DISCOUNT.getMessage());
        showTotalDiscountResult();
        showExpectedPaymentAmount(money);
        printResult(SHOW_BADGE.getMessage(), NONE_DISCOUNT.getMessage());
    }

    private void showBeforeDiscount(Integer money) {
        printResult(
                SHOW_BEFORE_DISCOUNT.getMessage(),
                format(MONEY_FORMAT.getMessage(), money)
        );
    }

    private void showPresentResult() {
        printResult(SHOW_PRESENT.getMessage(), THE_PRESENT.getMessage());
    }

    private void showTotalDiscountResult() {
        printResult(
                SHOW_TOTAL_DISCOUNT.getMessage(),
                format(MINUS_MONEY_FORMAT.getMessage(), abs(totalDiscountResult()))
        );
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
