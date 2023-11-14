package christmas.model.discount;

import static christmas.constants.PolicyNumbers.PRESENT_CUT_LINE;

import christmas.model.menu.OrderMenu;

public class Presentation {
    private final Integer totalMoney;

    private Presentation(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public static Presentation of (Integer totalMoney) {
        return new Presentation(totalMoney);
    }

    public Integer isPresent() {
        if (totalMoney >= PRESENT_CUT_LINE.getNumber()) {
            return new OrderMenu().getPresentGiftPrice();
        }

        return 0;
    }
}
