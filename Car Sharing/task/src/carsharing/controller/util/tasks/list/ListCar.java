package carsharing.controller.util.tasks.list;

import carsharing.controller.util.UtilManager;
import carsharing.controller.util.tasks.Task;
import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.service.company.CompanyServices;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ListCar implements Task {
    private final Company company;
    private final CompanyServices services;

    @Override
    public void perform(UtilManager manager) {
        final List<Car> cars = services.getAllCars(company);

        if (cars.isEmpty()) {
            System.out.println("The car list is empty!");

        } else {
            System.out.println("Car list:");
            for (int i = 1; i <= cars.size(); i++) {
                Car car = cars.get(i - 1);
                System.out.printf("%d. %s%n", i, car.getName());
            }
            System.out.println();
        }
    }
}
