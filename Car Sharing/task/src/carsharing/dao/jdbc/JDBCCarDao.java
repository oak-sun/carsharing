package carsharing.dao.jdbc;

import carsharing.dao.CarDao;
import carsharing.dao.CompanyDao;
import carsharing.model.Car;
import carsharing.model.DataBase;
import java.sql.SQLException;

public class JDBCCarDao implements CarDao {
    private static final CarDao INSTANCE = new JDBCCarDao();
    private static final CompanyDao companyDao = JDBCCompanyDao.getInstance();
    private final DataBase database;

    private JDBCCarDao() {
        this.database = DataBase.getInstance();
    }

    public static CarDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Car findById(int carId) throws SQLException {

        String FIND_BY_ID = "SELECT * FROM CAR WHERE ID = ?";
        try (var connection = database.getConnection();
             final var statement =
                     connection.prepareStatement(FIND_BY_ID)
        ) {
            statement.setInt(1, carId);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {

                final int id = resultSet.getInt("ID");
                final String name = resultSet.getString("NAME");
                final int companyId = resultSet.getInt("COMPANY_ID");
                final var company = companyDao.findById(companyId);
                return new Car(id, name, company);
            }
        }
        return null;
    }
}
