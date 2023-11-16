package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DDayDiscountTest {
    @DisplayName("방문일이 크리스마스 전인 경우 D-Day 할인 금액 반환, 아니면 0 반환")
    @ParameterizedTest
    @CsvSource(
            value = {
                    "1:1000", "3:1200",
                    "9:1800", "10:1900",
                    "16:2500", "17:2600",
                    "23:3200", "24:3300",
                    "25:3400", "26:0",
                    "30:0", "31:0"
            }, delimiter = ':'
    )
    void testSpecialDayDiscount(Integer day, Integer answer) {
        assertThat(DDayDiscount.of(day).checkDDAyDiscount()).isEqualTo(answer);
    }

    @AfterEach
    void tearDown() {
        Console.close();
    }


}