package carsharing.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./src/carsharing/db/";
    private static DataBase INSTANCE;
    private final String dbFileName;

    private DataBase(String dbFileName) {
        this.dbFileName = dbFileName;
    }

    public static DataBase getInstance(String dbFileName) {
        if (INSTANCE == null) {
            INSTANCE = new DataBase(dbFileName);
        }
        return INSTANCE;
    }

    public static DataBase getInstance() {
        return INSTANCE;
    }

    public void initialize() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (var connection = getConnection();
             var statement = connection.createStatement()) {
            connection.setAutoCommit(true);

            final var COMPANY_DDL =
                    "CREATE TABLE IF NOT EXISTS " +
                    "COMPANY(" +
                    "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(30) UNIQUE NOT NULL )";
            statement.executeUpdate(COMPANY_DDL);

            final var CAR_DDL =
                    "CREATE TABLE IF NOT EXISTS " +
                    "CAR (" +
                    "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(30) UNIQUE NOT NULL, " +
                    "COMPANY_ID INTEGER NOT NULL , " +
                    "CONSTRAINT fk_company FOREIGN KEY(COMPANY_ID) REFERENCES COMPANY(ID))";
            statement.executeUpdate(CAR_DDL);

            final var CUSTOMER_DDL =
                    "CREATE TABLE IF NOT EXISTS " +
                    "CUSTOMER(" +
                    "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(30) UNIQUE NOT NULL, " +
                    "RENTED_CAR_ID INTEGER default NULL, " +
                    "CONSTRAINT fk_car FOREIGN KEY(RENTED_CAR_ID) REFERENCES CAR(ID))";
            statement.executeUpdate(CUSTOMER_DDL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL + dbFileName);
    }
}