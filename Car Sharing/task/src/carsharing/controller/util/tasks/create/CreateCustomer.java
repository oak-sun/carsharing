package carsharing.controller.util.tasks.create;

import carsharing.controller.util.UtilManager;
import carsharing.controller.util.tasks.Task;
import carsharing.service.customer.CustomerService;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class CreateCustomer implements Task {

    private final CustomerService service;

    @Override
    public void perform(UtilManager manager) {
        System.out.println("Enter the customer name:");
        final var customerName = new Scanner(System.in).nextLine();
        service.add(customerName);
        System.out.println("The customer was added!");
    }
}