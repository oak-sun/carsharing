package carsharing.controller.util.tasks.list;

import carsharing.controller.Controller;
import carsharing.controller.util.UtilManager;
import carsharing.controller.util.menus.Menu;
import carsharing.controller.util.menus.MenuImpl;
import carsharing.controller.util.tasks.Switcher;
import carsharing.controller.util.tasks.Task;
import carsharing.controller.util.tasks.choose.ChooseCompany;
import carsharing.model.Company;
import carsharing.service.company.CompanyServices;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class ListCompany implements Task {

    private final CompanyServices services;

    @Override
    public void perform(UtilManager manager) {
        final List<Company> companies = services.getAll();

        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {

            System.out.println("Choose the company:");
            StringBuilder chooseCompanyMenuTextBuilder = new StringBuilder();
            final Map<Integer, Task> chooseCompanyMenuTaskMap = new HashMap<>();

            for (int i = 1; i <= companies.size(); i++) {
                Company company = companies.get(i - 1);
                chooseCompanyMenuTaskMap.put(i,
                        new ChooseCompany(company, services));
                chooseCompanyMenuTextBuilder
                        .append(String.format("%d. %s%n",
                                  i, company.getName()));
            }
            chooseCompanyMenuTextBuilder.append("0. Back");
            chooseCompanyMenuTaskMap.put(0,
                    new Switcher(Controller.getInstance().getManagerMenu()));
            Menu chooseCompanyMenu =
                    new MenuImpl(chooseCompanyMenuTextBuilder
                                                          .toString(),
                                                  chooseCompanyMenuTaskMap);
            manager.switcher(chooseCompanyMenu);
        }
    }
}