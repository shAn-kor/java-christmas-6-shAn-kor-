package christmas.exception;

public enum ErrorMessage {
    /*IS_NOT_INTEGER(" 숫자가 아닙니다."),
    IS_NOT_DAY(" 1 이상 31 이하 숫자를 입력해주세요"),
    HAS_SPACE(" 공백이 포함되었습니다."),
    NOTHING_PARSER(" 구분자 ( 콤마(,) , - ) 를 사용해 구분해주세요"),
    PARSER_START_OR_END(" 구분자 앞뒤로 문자가 있어야 합니다."),
    COUNT_UNDER_ONE(" 주문할 메뉴의 개수는 최소 1개 이상이어야 합니다."),
    MENU_SIZE_OVER(" 이벤트 기간동안 최대 20개의 메뉴를 주문할 수 있습니다."),
    NOT_FOR_SALE(" 판매하지 않는 메뉴입니다."),
    ONLY_DRINK(" 음료만 주문할 수 없습니다."),*/
    FAILED_INPUT_DAY("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    FAILED_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return this.errorMessage;
    }
}
