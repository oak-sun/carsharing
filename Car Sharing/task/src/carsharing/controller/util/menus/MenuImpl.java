package carsharing.controller.util.menus;

import carsharing.controller.util.UtilManager;
import carsharing.controller.util.tasks.Task;

import java.util.Map;
import java.util.Scanner;

public class MenuImpl implements Menu{
    protected final String displayText;
    protected final Map<Integer, Task> map;
    protected Task currentTask;

    public MenuImpl(String displayText, Map<Integer, Task> map) {
        this.displayText = displayText;
        this.map = map;
    }

    public void display() {
        System.out.println(displayText);
    }

    public void readInput() {
        final int actionCode = new Scanner(System.in).nextInt();
        currentTask = map.get(actionCode);
    }

    public void performAction(UtilManager manager) {
        currentTask.perform(manager);
    }
}
