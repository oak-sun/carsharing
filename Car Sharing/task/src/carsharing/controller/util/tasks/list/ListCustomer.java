package carsharing.controller.util.tasks.list;

import carsharing.controller.Controller;
import carsharing.controller.util.UtilManager;
import carsharing.controller.util.menus.Menu;
import carsharing.controller.util.menus.MenuImpl;
import carsharing.controller.util.tasks.Switcher;
import carsharing.controller.util.tasks.Task;
import carsharing.controller.util.tasks.choose.ChooseCustomer;
import carsharing.model.Customer;
import carsharing.service.customer.CustomerService;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class ListCustomer implements Task {

    private final CustomerService service;

    @Override
    public void perform(UtilManager manager) {

        final List<Customer> customers = service.getAll();
        if (customers.isEmpty()) {
            System.out.println("The customer list is empty!");

        } else {

            System.out.println("Customer list:");
            var chooseCustomerMenuTextBuilder = new StringBuilder();
            final Map<Integer, Task> chooseCustomerMenuMap = new HashMap<>();

            for (int i = 1; i <= customers.size(); i++) {
                var customer = customers.get(i - 1);
                chooseCustomerMenuMap.put(i, new ChooseCustomer(customer));
                chooseCustomerMenuTextBuilder.append(String.format("%d. %s%n", i, customer.getName()));
            }
            chooseCustomerMenuTextBuilder.append("0. Back");
            chooseCustomerMenuMap.put(0,
                    new Switcher(Controller.getInstance().getMainMenu()));
            var chooseCustomerMenu =
                    new MenuImpl(chooseCustomerMenuTextBuilder.toString(),
                            chooseCustomerMenuMap);
            manager.switcher(chooseCustomerMenu);
        }
    }
}