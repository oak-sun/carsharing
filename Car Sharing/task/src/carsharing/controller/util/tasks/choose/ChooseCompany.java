package carsharing.controller.util.tasks.choose;

import carsharing.controller.Controller;
import carsharing.controller.util.UtilManager;
import carsharing.controller.util.menus.Menu;
import carsharing.controller.util.menus.MenuImpl;
import carsharing.controller.util.tasks.Switcher;
import carsharing.controller.util.tasks.Task;
import carsharing.controller.util.tasks.create.CreateCar;
import carsharing.controller.util.tasks.list.ListCar;
import carsharing.model.Company;
import carsharing.service.company.CompanyServices;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class ChooseCompany implements Task {
    private final Company company;
    private final CompanyServices services;

    @Override
    public void perform(UtilManager manager) {
        System.out.printf("'%s' company%n", company.getName());

        String companyMenuText = """
                1. Car list
                2. Create a car
                0. Back""";
        var backTask = new Switcher(Controller.getInstance().getManagerMenu());
        var listCarTask = new ListCar(company, services);
        var createCarTask = new CreateCar(company, services);

        final Map<Integer, Task> companyMenuActionMap = new HashMap<>();
        companyMenuActionMap.put(1, listCarTask);
        companyMenuActionMap.put(2, createCarTask);
        companyMenuActionMap.put(0, backTask);
        Menu companyMenu = new MenuImpl(companyMenuText, companyMenuActionMap);
        manager.switcher(companyMenu);
    }
}