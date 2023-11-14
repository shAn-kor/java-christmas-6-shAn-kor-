package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static christmas.constants.ShowingMessage.INPUT_DAY;
import static christmas.constants.ShowingMessage.INPUT_MENU;

public class InputView {
    public static String readDate() {
        System.out.println(INPUT_DAY.getMessage());
        return readLine();
    }

    public static String inputMenu() {
        System.out.println(INPUT_MENU.getMessage());
        return readLine();
    }
}
