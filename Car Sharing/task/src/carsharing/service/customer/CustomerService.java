package carsharing.service.customer;

import carsharing.model.Car;
import carsharing.model.Customer;
import java.util.List;

public interface CustomerService {

    List<Customer> getAll();

    void add(String customerName);

    void rentCar(Customer customer, Car rentedCar);

    void returnCar(Customer customer);
}
