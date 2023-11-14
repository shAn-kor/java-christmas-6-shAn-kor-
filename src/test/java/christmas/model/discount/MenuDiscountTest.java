package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuDiscountTest {
    @DisplayName("주말이고 메뉴가 메인 메뉴인 경우, 평일이고 메뉴가 디저트인 경우 메뉴 타입 반환 / 아니면 없음 반환")
    @ParameterizedTest
    @CsvSource(
            value = {
                    "1:Main:Main", "1:Dessert:없음", "3:Main:없음","3:Dessert:Dessert"
            }, delimiter = ':'
    )
    void testMenuDiscount(Integer day, String type, String answer) {
        assertThat(MenuDiscount.of(day).calculateDiscount(type)).isEqualTo(answer);
    }
}