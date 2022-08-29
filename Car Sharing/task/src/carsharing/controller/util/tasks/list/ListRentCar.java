package carsharing.controller.util.tasks.list;

import carsharing.controller.Controller;
import carsharing.controller.util.UtilManager;
import carsharing.controller.util.menus.MenuImpl;
import carsharing.controller.util.tasks.Rent;
import carsharing.controller.util.tasks.Switcher;
import carsharing.controller.util.tasks.Task;
import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.service.company.CompanyServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListRentCar implements Task {
    private final Company company;
    private final Customer customer;
    private final CompanyServices service;

    public ListRentCar(Company company, Customer customer) {
        this.company = company;
        this.customer = customer;
        this.service = Controller.getInstance().getCompanyService();
    }

    @Override
    public void perform(UtilManager manager) {
        final List<Car> cars = service.getAllAvailableCars(company);

        if (cars.isEmpty()) {
            System.out.printf("No available cars in the '%s' company%n",
                    company.getName());
        } else {
            System.out.println("Choose a car:");
            var chooseCarMenuTextBuilder = new StringBuilder();
            final Map<Integer, Task> chooseCarMenuMap = new HashMap<>();

            for (int i = 1; i <= cars.size(); i++) {
                var car = cars.get(i - 1);
                chooseCarMenuMap.put(i, new Rent(car, customer));
                chooseCarMenuTextBuilder.append(String.format("%d. %s%n", i, car.getName()));
            }
            chooseCarMenuTextBuilder.append("0. Back");
            chooseCarMenuMap.put(0,
                    new Switcher(Controller.getInstance().getCustomerMenu()));
            var chooseCompanyMenu =
                    new MenuImpl(chooseCarMenuTextBuilder.toString(), chooseCarMenuMap);
            manager.switcher(chooseCompanyMenu);
        }
    }
}
