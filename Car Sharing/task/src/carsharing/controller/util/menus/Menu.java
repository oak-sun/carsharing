package carsharing.controller.util.menus;


import carsharing.controller.util.UtilManager;

public interface Menu {
    void display();
    void readInput();
    void performAction(UtilManager manager);
}
