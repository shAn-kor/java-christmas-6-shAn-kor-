package christmas.model.discount;

import static christmas.constants.PolicyNumbers.DISCOUNT_CUT_LINE;
import static christmas.constants.ShowingMessage.NONE_DISCOUNT;
import static christmas.model.discount.Discount.DISCOUNT_D_DAY;
import static christmas.model.discount.Discount.DISCOUNT_SPECIAL_DAY;
import static christmas.model.discount.Discount.DISCOUNT_WEEK;
import static christmas.model.discount.Discount.DISCOUNT_WEEKEND;
import static christmas.model.discount.Discount.GIVEN_PRESENT;
import static christmas.model.discount.Discount.canDiscount;
import static christmas.model.discount.Discount.noDiscount;
import static christmas.model.discount.Discount.setDiscountZero;
import static christmas.model.discount.DiscountResult.getDiscountResultList;
import static christmas.model.discount.DiscountResult.getExpectedPaymentAmount;
import static christmas.model.discount.DiscountResult.totalDiscountResult;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
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

    @DisplayName("결제 금액이 할인 적용 기준 금액 이상일 경우에만 할인 목록 계산해 반환한다.")
    @ParameterizedTest
    @MethodSource("setDiscountResultList")
    void testDiscountResultList(List<Integer> numbers, Integer totalMoney, String answer) {
        //given
        setDiscount(numbers);

        //when
        if (totalMoney < DISCOUNT_CUT_LINE.getNumber()) {
            noDiscount();
        }

        if (totalMoney > DISCOUNT_CUT_LINE.getNumber()) {
            canDiscount();
        }

        //then
        assertThat(getDiscountResultList()).isEqualTo(answer);
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

    static Stream<Arguments> setDiscountResultList() {
        return Stream.of(
                Arguments.arguments(List.of(1,1,0,1,1), 9, NONE_DISCOUNT.getMessage()),
                Arguments.arguments(List.of(0,0,50000,0,0), 1000000, "평일 할인: -50,000원\n")
        );
    }

    private static void setDiscount (List<Integer> money) {
        DISCOUNT_D_DAY.setMoney(money.get(0));
        DISCOUNT_SPECIAL_DAY.setMoney(money.get(1));
        DISCOUNT_WEEK.setMoney(money.get(2));
        DISCOUNT_WEEKEND.setMoney(money.get(3));
        GIVEN_PRESENT.setMoney(money.get(4));
    }

    @AfterAll
    static void tearDown() {
        setDiscountZero();
        Console.close();
    }
}