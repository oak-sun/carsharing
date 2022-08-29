package carsharing.dao;

import carsharing.model.Car;
import carsharing.model.Customer;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    List<Customer> getAll() throws SQLException;

    void add(String customerName) throws SQLException;

    void update(Customer customer, Car rentedCar) throws SQLException;
}
