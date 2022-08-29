package carsharing.dao;

import carsharing.model.Car;
import java.sql.SQLException;

public interface CarDao {
    Car findById(int carId) throws SQLException;
}