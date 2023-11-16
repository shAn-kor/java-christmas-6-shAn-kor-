package christmas.controller;

import static christmas.util.Convertor.convertMapValues;
import static christmas.util.InputHandler.deliverValidatedDate;
import static christmas.util.InputHandler.deliverValidatedMenu;
import static christmas.view.OutputView.printMenu;
import static christmas.view.OutputView.printPreview;
import static christmas.view.OutputView.printStart;

import christmas.model.menu.OrderMenu;
import java.util.Map;

public class Controller {
    public void run() {
        printStart();
        Integer day = deliverValidatedDate();
        Map<String, Integer> orderedMenus = initMenus();
        printPreview(day);
        printMenu(orderedMenus);
        OrderMenu.orderFoods(orderedMenus);

        checkResult(day);
    }

    private Map<String, Integer> initMenus() {
        return convertMapValues(deliverValidatedMenu());
    }

    private void checkResult(Integer day) {
        DiscountController discountController = new DiscountController();
        discountController.checkDiscount(day);
        discountController.showResult();
    }
}
