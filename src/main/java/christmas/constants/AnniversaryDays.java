package christmas.constants;

import java.util.List;

public enum AnniversaryDays {
    WEEKEND(List.of(1,2,8,9,15,16,22,23,29,30)),
    SPECIAL_DAY(List.of(3, 10, 17, 24, 25, 31));


    private final List<Integer> days;

    AnniversaryDays(List<Integer> days) {
        this.days = days;
    }

    public List<Integer> getDays() {
        return days;
    }
}
