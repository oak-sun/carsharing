package carsharing.controller.util.tasks.create;

import carsharing.controller.util.UtilManager;
import carsharing.controller.util.tasks.Task;
import carsharing.model.Company;
import carsharing.service.company.CompanyServices;
import lombok.AllArgsConstructor;

import java.util.Scanner;
@AllArgsConstructor
public class CreateCar implements Task {
    private final Company company;
    private final CompanyServices services;

    @Override
    public void perform(UtilManager manager) {

        System.out.println("Enter the car name:");
        final String carName = new Scanner(System.in).nextLine();
        services.addCar(company, carName);
        System.out.println("The car was added!");
    }
}
