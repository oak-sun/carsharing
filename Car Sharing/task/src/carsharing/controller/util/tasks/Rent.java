package carsharing.controller.util.tasks;

import carsharing.controller.Controller;
import carsharing.controller.util.UtilManager;
import carsharing.model.Car;
import carsharing.model.Customer;
import carsharing.service.customer.CustomerService;

public class Rent implements Task {
    private final Car car;
    private final Customer customer;
    private final CustomerService service;

    public Rent(Car car, Customer customer) {
        this.car = car;
        this.customer = customer;
        this.service = Controller
                                 .getInstance()
                                 .getCustomerService();
    }

    @Override
    public void perform(UtilManager manager) {
        service.rentCar(customer, car);
        System.out.printf("You rented '%s'%n", car.getName());
        manager.switcher(Controller.getInstance().getCustomerMenu());
    }
}
