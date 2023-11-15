package christmas.model.menu;

import static christmas.model.menu.OrderMenu.getTotalOrderAmount;
import static christmas.model.menu.OrderMenu.orderFoods;
import static christmas.model.menu.OrderMenu.setCountZero;
import static christmas.model.menu.RestaurantMenu.CHOCO_CAKE;
import static christmas.model.menu.RestaurantMenu.RED_WINE;
import static christmas.util.Convertor.convertMapValues;
import static christmas.util.Convertor.convertMenus;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import camp.nextstep.edu.missionutils.Console;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OrderMenuTest {

    @DisplayName("메뉴를 주문 받고 총 금액 반환한다.")
    @ParameterizedTest
    @MethodSource("setOrder")
    void testOrderAmount(Map<String, Integer> orderedMenus, Integer totalAmount) {
        orderFoods(orderedMenus);

        assertThat(getTotalOrderAmount()).isEqualTo(totalAmount);
    }


    static Stream<Arguments> setOrder() {
        return Stream.of(
                arguments(setMenus("레드와인-2"), RED_WINE.getPrice() * 2),
                arguments(setMenus("레드와인-2,초코케이크-3"), RED_WINE.getPrice() * 2 + CHOCO_CAKE.getPrice() * 3)
        );
    }

    private static Map<String, Integer> setMenus(String input) {
        return convertMapValues(convertMenus(input));
    }

    @AfterEach
    void tearDown() {
        setCountZero();
        Console.close();
    }
}