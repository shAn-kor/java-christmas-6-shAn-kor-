package christmas.model.discount;

public enum Discount {
    DISCOUNT_D_DAY("크리스마스 디데이 할인"),
    DISCOUNT_WEEK("평일 할인"),
    DISCOUNT_WEEKEND("주말 할인"),
    DISCOUNT_SPECIAL_DAY("특별 할인"),
    GIVEN_PRESENT("증정 이벤트");

    private static Boolean noneDiscount = false;
    private final String message;

    private Integer money;

    Discount(String message) {
        this.message = message;
        this.money = 0;
    }

    public static void noDiscount() {
        noneDiscount = true;
    }

    public static Boolean getNoneDiscount() {
        return noneDiscount;
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
}
