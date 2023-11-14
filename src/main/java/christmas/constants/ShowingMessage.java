package christmas.constants;

import static christmas.constants.PolicyNumbers.MONTH;
import static christmas.model.menu.RestaurantMenu.CHAMPAGNE;

public enum ShowingMessage {
    APPETIZER("Appetizer"),
    MAIN("Main"),
    DESSERT("Dessert"),
    DRINK("Drink"),
    THE_PRESENT(CHAMPAGNE.getName() + " 1개"),
    SHOW_WELCOME("안녕하세요! 우테코 식당 " + MONTH.getNumber() + "월 이벤트 플래너입니다."),
    INPUT_DAY(MONTH.getNumber() + "월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INPUT_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    SHOW_PREVIEW(MONTH.getNumber() + "월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    SHOW_BEFORE_DISCOUNT("\n<할인 전 총주문 금액>"),
    SHOW_PRESENT("\n<증정 메뉴>"),
    SHOW_ALL_ORDER_MENU("\n<주문 메뉴>"),
    SHOW_ALL_DISCOUNT("\n<혜택 내역>"),
    SHOW_TOTAL_DISCOUNT("\n<총혜택 금액>"),
    EXPECTED_PAYMENT_AMOUNT("\n<할인 후 예상 결제 금액>"),
    SHOW_BADGE(String.format("\n<%d월 이벤트 배지>", MONTH.getNumber())),
    RESULT_PARSER(": "),
    MENU_PARSER(","),
    NAME_COUNT_PARSER("-"),
    SPACE(" "),
    WON("원"),
    MONEY_FORMAT("%,d" + WON.getMessage()),
    SHOW_ORDERED_FORMAT("%s %d개"),
    MINUS_MONEY_FORMAT("-%,d" + WON.getMessage()),
    NONE_DISCOUNT("없음");

    private final String message;

    ShowingMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
