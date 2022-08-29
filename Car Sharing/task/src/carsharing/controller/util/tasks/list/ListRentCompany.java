package carsharing.controller.util.tasks.list;

import carsharing.controller.Controller;
import carsharing.controller.util.UtilManager;
import carsharing.controller.util.menus.MenuImpl;
import carsharing.controller.util.tasks.Switcher;
import carsharing.controller.util.tasks.Task;
import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.service.company.CompanyServices;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListRentCompany implements Task {
    private final Customer customer;
    private final CompanyServices service;
    public ListRentCompany(Customer customer) {
        this.customer = customer;
        this.service = Controller
                                .getInstance()
                               .getCompanyService();
    }


    @Override
    public void perform(UtilManager manager) {
        final List<Company> companies = service.getAll();

        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");

        } else if (customer.getRentedCar() != null) {
            System.out.println("You've already rented a car!");

        } else {
            System.out.println("Choose a company:");
            var chooseCompanyMenuTextBuilder = new StringBuilder();
            final Map<Integer, Task> chooseCompanyMenuMap = new HashMap<>();

            for (int i = 1; i <= companies.size(); i++) {

                var company = companies.get(i - 1);
                chooseCompanyMenuMap.put(i,
                        new ListRentCar(company, customer));
                chooseCompanyMenuTextBuilder.append(String.format("%d. %s%n",
                        i, company.getName()));
            }
            chooseCompanyMenuTextBuilder.append("0. Back");
            chooseCompanyMenuMap.put(0, new Switcher(Controller
                                                           .getInstance()
                                                           .getCustomerMenu()));
            var chooseCompanyMenu =
                    new MenuImpl(chooseCompanyMenuTextBuilder.toString(),
                            chooseCompanyMenuMap);
            manager.switcher(chooseCompanyMenu);
        }
    }
}
