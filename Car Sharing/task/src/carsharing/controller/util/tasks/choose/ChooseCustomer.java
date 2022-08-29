package carsharing.controller.util.tasks.choose;

import carsharing.controller.Controller;
import carsharing.controller.util.UtilManager;
import carsharing.controller.util.menus.MenuImpl;
import carsharing.controller.util.tasks.Return;
import carsharing.controller.util.tasks.Switcher;
import carsharing.controller.util.tasks.Task;
import carsharing.controller.util.tasks.YourCar;
import carsharing.controller.util.tasks.list.ListRentCompany;
import carsharing.model.Customer;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class ChooseCustomer implements Task {
    private final Customer customer;

    @Override
    public void perform(UtilManager manager) {
        final var customerMenuText =
                """
                        1. Rent a car
                        2. Return a rented car
                        3. My rented car
                        0. Back""";
        var backTask = new Switcher(Controller.getInstance().getMainMenu());
        Map<Integer, Task> customerMenuMap = new HashMap<>();

        var listCompanyForRent = new ListRentCompany(customer);
        var returnCar = new Return(customer);
        var yourCar = new YourCar(customer);

        customerMenuMap.put(1, listCompanyForRent);
        customerMenuMap.put(2, returnCar);
        customerMenuMap.put(3, yourCar);
        customerMenuMap.put(0, backTask);

        var customerMenu = new MenuImpl(customerMenuText, customerMenuMap);
        Controller
                .getInstance()
                .setCustomerMenu(customerMenu);
        manager.switcher(customerMenu);
    }
}