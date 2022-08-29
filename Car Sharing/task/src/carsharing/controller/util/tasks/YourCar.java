package carsharing.controller.util.tasks;

import carsharing.controller.util.UtilManager;
import carsharing.model.Customer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class YourCar implements Task {
    private final Customer customer;

    @Override
    public void perform(UtilManager manager) {
        final var rentedCar = customer.getRentedCar();

        if (rentedCar == null) {
            System.out.println("You didn't rent a car!");
        } else {

            System.out.println("Your rented car:");
            System.out.println(rentedCar.getName());
            final var company = rentedCar.getCompany();
            System.out.println("Company:");
            System.out.println(company.getName());
        }
    }
}
