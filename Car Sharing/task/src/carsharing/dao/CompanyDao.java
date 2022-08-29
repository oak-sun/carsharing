package carsharing.dao;

import carsharing.model.Car;
import carsharing.model.Company;
import java.sql.SQLException;
import java.util.List;

public interface CompanyDao {
    void add(String companyName) throws SQLException;

    List<Company> getAll() throws SQLException;

    void addCar(Company company, String carName) throws SQLException;

    List<Car> getAllCars(Company company) throws SQLException;
    List<Car> getAllAvailableCars(Company company) throws SQLException;

    Company findById(int id) throws SQLException;
}