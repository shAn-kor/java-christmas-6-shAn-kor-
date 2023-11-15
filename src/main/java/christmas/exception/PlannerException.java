package christmas.exception;

public class PlannerException extends IllegalArgumentException{
    private static final String errorStartMessage = "[ERROR] ";

    private PlannerException(ErrorMessage errorMessage, ErrorMessage errorMessageDetail) {
        super(errorStartMessage + errorMessage.getMessage() + errorMessageDetail.getMessage());
    }

    public static PlannerException of (ErrorMessage errorMessage, ErrorMessage errorMessageDetail) {
        return new PlannerException(errorMessage, errorMessageDetail);
    }
}
