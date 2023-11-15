package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PresentationTest {
    @DisplayName("총 결제 금액이 증정 상품 제공 기준 이상이면 true 반환, 아니면 false 반환")
    @ParameterizedTest
    @CsvSource(
            value = {
                    "10000:false", "30000:false", "119999:false", "120000:true", "999999999:true"
            }, delimiter = ':'
    )
    void testPresentation(Integer money, Boolean answer) {
        Presentation presentation = Presentation.of(money);

        assertThat(presentation.isPresent()).isEqualTo(answer);
    }

    @AfterEach
    void tearDown() {
        Console.close();
    }
}