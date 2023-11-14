package christmas.model.discount;

import static christmas.model.discount.Discount.DISCOUNT_D_DAY;
import static christmas.model.discount.Discount.DISCOUNT_SPECIAL_DAY;
import static christmas.model.discount.Discount.DISCOUNT_WEEK;
import static christmas.model.discount.Discount.DISCOUNT_WEEKEND;
import static christmas.model.discount.Discount.GIVEN_PRESENT;
import static christmas.model.discount.DiscountResult.getExpectedPaymentAmount;
import static christmas.model.discount.DiscountResult.totalDiscountResult;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DiscountResultTest {
    @DisplayName("총 할인 받은 금액 계산해 반환한다.")
    @ParameterizedTest
    @MethodSource("setDiscountResult")
    void testTotalDiscountResult(List<Integer> numbers, Integer answer) {
        setDiscount(numbers);

        assertThat(totalDiscountResult()).isEqualTo(answer);
    }

    @DisplayName("결제 금액에서 증정품 제외 할인 금액 계산해 반환한다.")
    @ParameterizedTest
    @MethodSource("setExpectedPaymentAmount")
    void testTotalDiscountResult(List<Integer> numbers, Integer totalMoney, Integer answer) {
        setDiscount(numbers);

        assertThat(getExpectedPaymentAmount(totalMoney)).isEqualTo(answer);
    }

    static Stream<Arguments> setDiscountResult() {
        return Stream.of(
                Arguments.arguments(List.of(1,1,0,1,1), 4),
                Arguments.arguments(List.of(5,5,5,5,5), 25)
        );
    }

    static Stream<Arguments> setExpectedPaymentAmount() {
        return Stream.of(
                Arguments.arguments(List.of(1,1,0,1,1), 9, 6),
                Arguments.arguments(List.of(5,5,5,5,5), 30, 10)
        );
    }

    private static void setDiscount (List<Integer> money) {
        DISCOUNT_D_DAY.setMoney(money.get(0));
        DISCOUNT_SPECIAL_DAY.setMoney(money.get(1));
        DISCOUNT_WEEK.setMoney(money.get(2));
        DISCOUNT_WEEKEND.setMoney(money.get(3));
        GIVEN_PRESENT.setMoney(money.get(4));
    }
}