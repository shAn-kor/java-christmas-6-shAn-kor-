package christmas.model.discount;

import java.util.Arrays;

public enum Discount {
    DISCOUNT_D_DAY("크리스마스 디데이 할인"),
    DISCOUNT_WEEK("평일 할인"),
    DISCOUNT_WEEKEND("주말 할인"),
    DISCOUNT_SPECIAL_DAY("특별 할인"),
    GIVEN_PRESENT("증정 이벤트");

    private static Boolean ifCanDiscount = true;
    private final String message;

    private Integer money;

    Discount(String message) {
        this.message = message;
        this.money = 0;
    }

    public static void noDiscount() {
        ifCanDiscount = false;
    }

    public static void canDiscount() {
        ifCanDiscount = true;
    }

    public static Boolean getIfCanDiscount() {
        return ifCanDiscount;
    }

    public String getMessage() {
        return message;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public static void setDiscountZero() {
        Arrays.stream(Discount.values()).forEach(discount -> discount.setMoney(0));
    }
}
