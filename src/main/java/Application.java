import java.util.HashMap;

import controllers.EmployeeController;
import controllers.LoggedInController;
import controllers.ManagerController;
import controllers.ReimbursementController;
import daos.Dao;
import daos.MongoDao;

import io.javalin.Javalin;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import pages.*;


public class Application extends AbstractApplication {

    private Javalin javalin;

    Application(Javalin javalinApp) {
        javalin = javalinApp;
        init();
    }

    public void init() {
        context = new HashMap<String, Object>();
        Dao dao = new MongoDao();

        EmployeeController employeeController = new EmployeeController();
        ManagerController managerController = new ManagerController();
        LoggedInController loggedInController = new LoggedInController(employeeController, managerController);
        ReimbursementController reimbursementController = new ReimbursementController(employeeController, managerController);
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        Logger javalinLogger = Logger.getLogger("io.javalin.Javalin");
        Logger eclipseLogger = Logger.getLogger("org.eclipse.jetty");
        Logger thymeleafLogger = Logger.getLogger("org.thymeleaf");


        mongoLogger.setLevel(Level.FATAL);
        javalinLogger.setLevel(Level.FATAL);
        eclipseLogger.setLevel(Level.FATAL);
        thymeleafLogger.setLevel(Level.FATAL);

        context.put("Dao", dao);
        context.put("EmployeeController", employeeController);
        context.put("ManagerController", managerController);
        context.put("LoggedInController", loggedInController);
        context.put("ReimbursementController", reimbursementController);

    }
    @Override
    public void run() {
        init();

        (new EmployeePages(javalin, context)).doPage();
        (new HomePage(javalin, context)).doPage();
        (new ManagerPages(javalin, context)).doPage();

    }

    public void stop() {
        javalin.stop();
    }
}
