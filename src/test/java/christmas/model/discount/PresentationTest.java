package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PresentationTest {
    @DisplayName("총 결제 금액이 증정 상품 제공 기준 이상이면 증정 상품 금액 반환, 아니면 0 반환")
    @ParameterizedTest
    @CsvSource(
            value = {
                    "10000:0", "30000:0", "119999:0", "120000:25000", "999999999:25000"
            }, delimiter = ':'
    )
    void testSpecialDayDiscount(Integer money, Integer answer) {
        Presentation presentation = Presentation.of(money);

        assertThat(presentation.isPresent()).isEqualTo(answer);
    }
}