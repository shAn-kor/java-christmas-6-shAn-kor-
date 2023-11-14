package christmas.exception;

public class PlannerException extends IllegalArgumentException{
    private static final String errorStartMessage = "[ERROR] ";

    private PlannerException(ErrorMessage errorMessage) {
        super(errorStartMessage + errorMessage.getMessage());
    }

    public static PlannerException of (ErrorMessage errorMessage) {
        return new PlannerException(errorMessage);
    }
}
