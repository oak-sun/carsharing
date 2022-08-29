package carsharing.controller.util;

import carsharing.controller.util.menus.Menu;

public class UtilManager implements Runnable {
    private Menu menu;
    private boolean shouldClosed;

    public UtilManager(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void run() {

        while (!shouldClosed) {
            menu.display();
            menu.readInput();
            menu.performAction(this);
        }
    }

    public void switcher(Menu menu) {
        this.menu = menu;
    }

    public void close() {
        shouldClosed = true;
    }
}
