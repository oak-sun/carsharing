package carsharing.service.company;

import carsharing.model.Car;
import carsharing.model.Company;

import java.util.List;

public interface CompanyServices {

    void add(String companyName);

    List<Company> getAll();

    void addCar(Company company, String carName);

    List<Car> getAllCars(Company company);

    List<Car> getAllAvailableCars(Company company);
}