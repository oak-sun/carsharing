package carsharing.service.company;

import carsharing.dao.CompanyDao;
import carsharing.model.Car;
import carsharing.model.Company;
import lombok.AllArgsConstructor;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
public class CompanyServiceImpl implements CompanyServices {
    private final CompanyDao dao;

    @Override
    public void add(String companyName) {
        try {
            dao.add(companyName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Company> getAll() {
        try {
            return dao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCar(Company company, String carName) {
        try {
            dao.addCar(company, carName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> getAllCars(Company company) {
        try {
            return dao.getAllCars(company);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> getAllAvailableCars(Company company) {
        try {
            return dao.getAllAvailableCars(company);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}