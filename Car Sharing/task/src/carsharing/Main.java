package carsharing;

import carsharing.controller.Controller;
import carsharing.model.DataBase;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        var dbFileName = "test";

        if (args.length > 1) {
            if ("-databaseFileName".equalsIgnoreCase(args[0])) {
                dbFileName = args[1];
            }
        }
        var database = DataBase.getInstance(dbFileName);
        database.initialize();
        var controller = Controller.getInstance();
        var manager = controller.getUtilManager();
        manager.run();
    }
}