package christmas.controller;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static christmas.exception.ErrorMessage.FAILED_INPUT_DAY;
import static christmas.exception.ErrorMessage.FAILED_ORDER;
import static christmas.model.discount.Discount.setDiscountZero;
import static christmas.model.menu.OrderMenu.setCountZero;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ControllerTest extends NsTest {

    @DisplayName("입력 예외 후 다시 동작 확인")
    @Test
    void testExceptionAndRetry() {
        assertSimpleTest(() -> {
            run("ㅁ", "3", "asd-d", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    FAILED_INPUT_DAY.getMessage(),
                    FAILED_ORDER.getMessage(),
                    "<주문 메뉴>",
                    "<할인 전 총주문 금액>",
                    "<증정 메뉴>",
                    "<혜택 내역>",
                    "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>",
                    "<12월 이벤트 배지>"
            );
        });
    }

    @DisplayName("증정품만 없는 경우 출력")
    @Test
    void testDiscountWithoutPresent() {
        assertSimpleTest(() -> {
            run("3", "바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "<할인 전 총주문 금액>",
                    "<증정 메뉴>",
                    "없음",
                    "<혜택 내역>",
                    "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>",
                    "<12월 이벤트 배지>"
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

    @AfterEach
    void tearDown() {
        setCountZero();
        setDiscountZero();
        Console.close();
    }
}