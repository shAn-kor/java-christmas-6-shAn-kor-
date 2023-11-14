package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.menu.OrderMenu;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuDiscountTest {
    /*@BeforeEach
    void reset() {
        //given
        Map<String, Integer> menus = new HashMap<>();
        menus.put("초코케이크", 1);
        menus.put("티본스테이크", 1);

        //when
        OrderMenu.orderFoods(menus);
    }*/

    @DisplayName("주말이고 메뉴가 메인 메뉴인 경우, 평일이고 메뉴가 디저트인 경우 할인 금액 반환 / 아니면 0 반환")
    @ParameterizedTest
    @CsvSource(
            value = {
                    "1:Main:2023", "1:Dessert:0", "3:Main:0","3:Dessert:2023"
            }, delimiter = ':'
    )
    void testSpecialDayDiscount(Integer day, String type, Integer answer) {
        //given
        Map<String, Integer> menus = new HashMap<>();
        menus.put("초코케이크", 1);
        menus.put("티본스테이크", 1);

        //when
        new OrderMenu().orderFoods(menus);


        //then
        assertThat(MenuDiscount.of(day).calculateDiscount(type, 1)).isEqualTo(answer);
    }
}