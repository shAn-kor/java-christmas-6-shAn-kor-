package christmas.util;

import static christmas.exception.ErrorMessage.FAILED_INPUT_DAY;
import static christmas.exception.ErrorMessage.FAILED_ORDER;
import static christmas.util.Convertor.convertMenus;
import static christmas.util.Validator.validateDateIsInteger;
import static christmas.util.Validator.validateInputForm;
import static christmas.util.Validator.validateLegalDay;
import static christmas.util.Validator.validateMenu;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.PlannerException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ValidatorTest {
    @DisplayName("날짜 입력 시 숫자가 아닐 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings={"abc", "wef","d2","d-d"})
    void testInputDateNotInteger(String input) {
        assertThatThrownBy(() -> validateDateIsInteger(input))
                .isInstanceOf(PlannerException.class)
                .hasMessageContaining(FAILED_INPUT_DAY.getMessage());
    }

    @DisplayName("날짜 입력 시 숫자이면 예외 없음")
    @ParameterizedTest
    @ValueSource(strings={"1", "123","2341","5454"})
    void testInputDateInteger(String input) {
        assertThatCode(() -> validateDateIsInteger(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("날짜 입력 시 1~31 숫자가 아닐 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(ints={234, 0,32,50})
    void testInputDateNotDay(Integer input) {
        assertThatThrownBy(() -> validateLegalDay(input))
                .isInstanceOf(PlannerException.class)
                .hasMessageContaining(FAILED_INPUT_DAY.getMessage());
    }

    @DisplayName("날짜 입력 시 1~31 숫자이면 예외 없음")
    @ParameterizedTest
    @ValueSource(ints={3, 1,31,22})
    void testInputDateDay(Integer input) {
        assertThatCode(() -> validateLegalDay(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("메뉴 입력 시 입력 형식 규정과 다를 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings={"abc", "we-f,","d 2"})
    void testInputMenuIllegal(String input) {
        assertThatThrownBy(() -> validateInputForm(input))
                .isInstanceOf(PlannerException.class)
                .hasMessageContaining(FAILED_ORDER.getMessage());
    }

    @DisplayName("메뉴 입력 시 입력 형식 규정 지킬 경우 예외 없음")
    @ParameterizedTest
    @ValueSource(strings={"ab-c", "we-f,we-f"})
    void testInputMenu(String input) {
        assertThatCode(() -> validateInputForm(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("메뉴 입력 시 없는 메뉴, 중복 메뉴, 음료만 주문, 개수 초과 등 규정과 다를 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings={"새송이수프-1", "레드와인-2","양송이수프-31","양송이수프-1,양송이수프-3","양송이수프-ㅁ,레드와인-3","양송이수프-0"})
    void testMenuIllegal(String input) {
        assertThatThrownBy(() -> validateMenu(convertMenus(input)))
                .isInstanceOf(PlannerException.class)
                .hasMessageContaining(FAILED_ORDER.getMessage());
    }

    @DisplayName("메뉴 입력 시 주문 규정 지킬 경우 예외 없음")
    @ParameterizedTest
    @ValueSource(strings={"양송이수프-3,제로콜라-3", "양송이수프-3,레드와인-2"})
    void testMenu(String input) {

        assertThatCode(() -> validateMenu(convertMenus(input)))
                .doesNotThrowAnyException();
    }

    @AfterEach
    void tearDown() {
        Console.close();
    }
}