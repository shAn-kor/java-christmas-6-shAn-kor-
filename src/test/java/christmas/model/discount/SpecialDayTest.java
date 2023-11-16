package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialDayTest {
    @DisplayName("방문일이 달력에 등록된 특별 할인하는 날인 경우 할인 금액 반환, 아니면 0 반환")
    @ParameterizedTest
    @CsvSource(
            value = {
            "1:0", "3:1000",
            "9:0", "10:1000",
            "16:0", "17:1000",
            "23:0", "24:1000",
            "26:0", "25:1000",
            "30:0", "31:1000"
            }, delimiter = ':'
    )
    void testSpecialDayDiscount(Integer day, Integer answer) {
        assertThat(SpecialDay.of(day).specialDayDiscount()).isEqualTo(answer);
    }

    @AfterEach
    void tearDown() {
        Console.close();
    }
}