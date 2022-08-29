package carsharing.dao.jdbc;

import carsharing.dao.CarDao;
import carsharing.dao.CustomerDao;
import carsharing.model.Car;
import carsharing.model.Customer;
import carsharing.model.DataBase;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class JDBCCustomerDao implements CustomerDao {
    private static final CustomerDao INSTANCE = new JDBCCustomerDao();
    private static final CarDao carDao = JDBCCarDao.getInstance();
    private static final String INSERT_CUSTOMER_STATEMENT =
            "INSERT INTO CUSTOMER(NAME) VALUES (?)";
    private static final String QUERY_FIND_ALL_CUSTOMER =
            "SELECT * from CUSTOMER";
    private static final String UPDATE_CAR_STATEMENT =
            "UPDATE CUSTOMER SET RENTED_CAR_ID = ? where ID = ?";
    private final DataBase database;

    private JDBCCustomerDao() {
        database = DataBase.getInstance();
    }

    public static CustomerDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Customer> getAll() throws SQLException {

        List<Customer> customers = new ArrayList<>();

        try (var connection = database.getConnection();
             var statement =
                     connection.prepareStatement(QUERY_FIND_ALL_CUSTOMER);
             var rs = statement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                final int rentedCarId = rs.getInt("RENTED_CAR_ID");
                Car rentedCar = null;
                if (rentedCarId != 0) {
                    rentedCar = carDao.findById(rentedCarId);
                }
                customers.add(new Customer(id, name, rentedCar));
            }
            return customers;
        }
    }

    @Override
    public void add(String customerName) throws SQLException {
        try (var connection = database.getConnection();
             var statement =
                     connection.prepareStatement(INSERT_CUSTOMER_STATEMENT)) {
            statement.setString(1, customerName);
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Customer customer, Car rentedCar) throws SQLException {

        try (var connection = database.getConnection();
             var statement =
                     connection.prepareStatement(UPDATE_CAR_STATEMENT)) {
            statement.setInt(2, customer.getId());

            if (rentedCar != null) {
                statement.setInt(1, rentedCar.getId());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            statement.executeUpdate();
        }
    }
}
