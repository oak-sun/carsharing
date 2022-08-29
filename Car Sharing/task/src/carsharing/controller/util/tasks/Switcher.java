package carsharing.controller.util.tasks;

import carsharing.controller.util.UtilManager;
import carsharing.controller.util.menus.Menu;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Switcher implements Task {
    private final Menu nextMenu;

    @Override
    public void perform(UtilManager manager) {
        manager.switcher(nextMenu);
    }
}