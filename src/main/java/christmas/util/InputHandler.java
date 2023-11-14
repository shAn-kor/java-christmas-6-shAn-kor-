package christmas.util;

import static christmas.view.OutputView.printErrorMessageFor;

import christmas.exception.PlannerException;
import christmas.view.InputView;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class InputHandler {
    public static Map<String, String> deliverValidatedMenu() {
        return deliverValidatedInput(
                InputView::inputMenu,
                Validator::validateInputForm,
                Convertor::convertMenus,
                Validator::validateMenu
        );
    }

    public static Integer deliverValidatedDate() {
        return deliverValidatedInput(
                InputView::readDate,
                Validator::validateDateIsInteger,
                Convertor::convertStringToInteger,
                Validator::validateLegalDay
        );
    }

    private static <T> T deliverValidatedInput(
            Supplier<String> inputSupplier,
            Consumer<String> inputValidator,
            Function<String, T> resultConverter,
            Consumer<T> validator
    ) {
        while (true) {
            try {
                String input = inputSupplier.get();
                inputValidator.accept(input);
                T convertedInput = resultConverter.apply(input);
                validator.accept(convertedInput);
                return convertedInput;
            } catch (PlannerException exception) {
                printErrorMessageFor(exception);
            }
        }
    }
}
