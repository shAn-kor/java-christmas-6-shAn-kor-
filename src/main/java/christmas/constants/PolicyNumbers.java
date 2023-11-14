package christmas.constants;

public enum PolicyNumbers {
    MONTH(12),
    MONTH_LAST_DAY(31),
    MONTH_FIRST_DAY(1),
    DISCOUNT_CUT_LINE(10_000),
    PRESENT_CUT_LINE(120_000),
    XMAS_D_DAY_DISCOUNT_BASE(1_000),
    D_Day_DISCOUNT_DAY(25),
    MENU_COUNT_LIMIT(20),
    FOOD_DISCOUNT_PRICE(2023),
    SPECIAL_DAY_DISCOUNT_PRICE(1000),
    XMAS_D_DAY_DISCOUNT_ADD(100);

    private final Integer number;

    PolicyNumbers(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }
}
