package carsharing.controller.util.tasks.create;

import carsharing.controller.util.UtilManager;
import carsharing.controller.util.tasks.Task;
import carsharing.service.company.CompanyServices;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class CreateCompany implements Task {
    private final CompanyServices services;

    @Override
    public void perform(UtilManager manager) {

        System.out.println("Enter the company name:");
        final String companyName = new Scanner(System.in).nextLine();
        services.add(companyName);
        System.out.println("The company was created!");
    }
}
