package christmas.model;

import static christmas.model.Badge.getBadge;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {
    @DisplayName("결제 금액을 기준과 비교해 적절한 배지를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "1000:없음", "4999:없음",
            "5000:별", "9999:별",
            "10000:트리", "19999:트리",
            "20000:산타", "999999:산타"
    }, delimiter = ':')
    void testBadge(Integer money, String badge) {
        assertThat(getBadge(money)).isEqualTo(badge);
    }

    @AfterEach
    void tearDown() {
        Console.close();
    }
}