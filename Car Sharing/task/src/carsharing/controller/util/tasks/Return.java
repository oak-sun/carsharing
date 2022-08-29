package carsharing.controller.util.tasks;

import carsharing.controller.Controller;
import carsharing.controller.util.UtilManager;
import carsharing.model.Customer;
import carsharing.service.customer.CustomerService;


public class Return implements Task {
    private final Customer customer;
    private final CustomerService service;

    public Return(Customer customer) {
        this.customer = customer;
        this.service = Controller.getInstance().getCustomerService();
    }

    @Override
    public void perform(UtilManager manager) {
        if(customer.getRentedCar() == null) {
            System.out.println("You didn't rent a car!");
        } else {
            service.returnCar(customer);
            System.out.println("You've returned a rented car!");
        }
    }
}
