package carsharing.service.customer;

import carsharing.dao.CustomerDao;
import carsharing.model.Car;
import carsharing.model.Customer;
import lombok.AllArgsConstructor;

import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao dao;

    @Override
    public List<Customer> getAll() {
        try {
            return dao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(String customerName) {
        try {
            dao.add(customerName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rentCar(Customer customer, Car rentedCar) {
        try {
            dao.update(customer, rentedCar);
            customer.setRentedCar(rentedCar);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void returnCar(Customer customer) {
        try {
            dao.update(customer, null);
            customer.setRentedCar(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}