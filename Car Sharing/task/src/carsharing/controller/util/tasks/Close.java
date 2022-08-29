package carsharing.controller.util.tasks;

import carsharing.controller.util.UtilManager;

public class Close implements Task {
    @Override
    public void perform(UtilManager manager) {
        manager.close();
    }
}
