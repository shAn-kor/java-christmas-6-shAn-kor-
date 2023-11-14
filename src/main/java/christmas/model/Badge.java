package christmas.model;

import static christmas.constants.ShowingMessage.NONE_DISCOUNT;
import static java.util.Arrays.stream;

import java.util.Comparator;

public enum Badge {
    FIRST("산타", 50000),
    SECOND("트리", 10000),
    THIRD("별", 5000);

    private final String badgeName;

    private final Integer badgeCutLinePrice;

    Badge(String badgeName, Integer badgeCutLinePrice) {
        this.badgeName = badgeName;
        this.badgeCutLinePrice = badgeCutLinePrice;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public Integer getBadgeCutLinePrice() {
        return badgeCutLinePrice;
    }

    public static String getBadge (Integer money) {
        return stream(Badge.values())
                .filter(badge -> badge.getBadgeCutLinePrice() <= money)
                .max(Comparator.comparingInt(Badge::getBadgeCutLinePrice))
                .map(Badge::getBadgeName)
                .orElse(NONE_DISCOUNT.getMessage());
    }
}
