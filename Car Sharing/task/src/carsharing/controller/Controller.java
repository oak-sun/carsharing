package carsharing.controller;

import carsharing.controller.util.UtilManager;
import carsharing.controller.util.menus.Menu;
import carsharing.controller.util.menus.MenuImpl;
import carsharing.controller.util.tasks.Close;
import carsharing.controller.util.tasks.Switcher;
import carsharing.controller.util.tasks.Task;
import carsharing.controller.util.tasks.create.CreateCompany;
import carsharing.controller.util.tasks.create.CreateCustomer;
import carsharing.controller.util.tasks.list.ListCompany;
import carsharing.controller.util.tasks.list.ListCustomer;
import carsharing.dao.CompanyDao;
import carsharing.dao.jdbc.JDBCCompanyDao;
import carsharing.dao.jdbc.JDBCCustomerDao;
import carsharing.service.company.CompanyServiceImpl;
import carsharing.service.company.CompanyServices;
import carsharing.service.customer.CustomerService;
import carsharing.service.customer.CustomerServiceImpl;
import lombok.NoArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class Controller {
    private static Controller instance;
    private UtilManager manager;
    private Menu mainMenu;
    private Menu managerMenu;
    private Menu customerMenu;
    private CompanyDao dao;
    private CompanyServices companyService;
    private CustomerService customerService;

    public static Controller getInstance() {
        if (instance == null) {
            var controller = new Controller();
            controller.wireDependencies();
            instance = controller;
        }
        return instance;
    }

    public void wireDependencies() {
        dao = JDBCCompanyDao.getInstance();
        companyService = new CompanyServiceImpl(dao);
        customerService = new CustomerServiceImpl(JDBCCustomerDao.getInstance());
        var createCompanyTask = new CreateCompany(companyService);
        var listCompanyTask = new ListCompany(companyService);

        final var mainMenuDisplayText = """
                1. Log in as a manager
                2. Log in as a customer
                3. Create a customer
                0. Exit""";
        final Map<Integer, Task> mainMenuTaskMap = new HashMap<>();
        mainMenu = new MenuImpl(mainMenuDisplayText, mainMenuTaskMap);

        var backTask = new Switcher(mainMenu);
        final Map<Integer, Task> managerMenuTaskMap = new HashMap<>();

        managerMenuTaskMap.put(1, listCompanyTask);
        managerMenuTaskMap.put(2, createCompanyTask);
        managerMenuTaskMap.put(0, backTask);

        final var managerMenuDisplayText = """
                1. Company list
                2. Create a company
                0. Back""";
        managerMenu = new MenuImpl(managerMenuDisplayText, managerMenuTaskMap);
        var managerLoginTask = new Switcher(managerMenu);
        var listCustomerTask= new ListCustomer(customerService);
        var createCustomerTask = new CreateCustomer(customerService);
        var closeTask = new Close();

        mainMenuTaskMap.put(1, managerLoginTask);
        mainMenuTaskMap.put(2, listCustomerTask);
        mainMenuTaskMap.put(3, createCustomerTask);
        mainMenuTaskMap.put(0, closeTask);
        manager = new UtilManager(mainMenu);
    }

    public UtilManager getUtilManager() {
        return manager;
    }

    public Menu getMainMenu() {
        return mainMenu;
    }

    public Menu getManagerMenu() {
        return managerMenu;
    }

    public CompanyDao getCompanyDao() {
        return dao;
    }

    public CompanyServices getCompanyService() {
        return companyService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public Menu getCustomerMenu() {
        return customerMenu;
    }

    public void setCustomerMenu(Menu customerMenu) {
        this.customerMenu = customerMenu;
    }
}