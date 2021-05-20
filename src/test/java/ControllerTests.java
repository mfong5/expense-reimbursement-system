import controllers.EmployeeController;
import controllers.ManagerController;
import controllers.ReimbursementController;
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.javalin.Javalin;
import io.javalin.http.Context;

import static org.mockito.Mockito.*;

public class ControllerTests {
    private Context ctx = mock(Context.class);
    EmployeeController employeeController = new EmployeeController();
    ManagerController managerController = new ManagerController();
    ReimbursementController reimbursementController = new ReimbursementController(employeeController, managerController);
    @Test
    public void testGetAllEmployees() {
        employeeController.getAllEmployees(ctx);
    }

    @Test
    public void testGetEmployeeInformation() {
        employeeController.getEmployeeInformation(ctx);
    }

    @Test
    public void testEmployeeLogin() {
        when (ctx.formParam("username")).thenReturn("User");
        when (ctx.formParam("password")).thenReturn("Password");
        employeeController.employeeLogin(ctx);
    }

    @Test
    public void testManagerLogin() {
        when (ctx.formParam("username")).thenReturn("Michael");
        when (ctx.formParam("password")).thenReturn("Fong");
        managerController.managerLogin(ctx);
    }

    @Test
    public void testEmployeeSearch() {
        when (ctx.formParam("username")).thenReturn("User");
        managerController.employeeSearch(ctx);
    }

    @Test
    public void testGetEmployeeReimbursements() {
        reimbursementController.getEmployeeReimbursements(ctx);
    }
}
