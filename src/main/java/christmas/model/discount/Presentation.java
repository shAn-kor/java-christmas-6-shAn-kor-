package christmas.model.discount;

import static christmas.constants.PolicyNumbers.PRESENT_CUT_LINE;

public class Presentation {
    private final Integer totalMoney;

    private Presentation(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public static Presentation of (Integer totalMoney) {
        return new Presentation(totalMoney);
    }

    public Boolean isPresent() {
        return totalMoney >= PRESENT_CUT_LINE.getNumber();
    }
}
