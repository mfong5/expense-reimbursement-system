package pages;

import controllers.LoggedInController;
import controllers.ReimbursementController;
import io.javalin.Javalin;
import controllers.EmployeeController;

import java.util.HashMap;

/**
 * Class to hold the javalin get and post methods for employee related pages
 */
public class EmployeePages implements Page {
    private Javalin javalin;
    private HashMap<String, Object> context;

    public EmployeePages(Javalin javalinApp, HashMap<String, Object> appContext) {
        javalin = javalinApp;
        context = appContext;
    }

    @Override
    public void doPage() {
        javalin.post("/EmployeeInfo", ctx -> {((EmployeeController) context.get("EmployeeController")).getEmployeeInformation(ctx);});
        javalin.get("/Employees/ViewUserInfo", ctx -> {((LoggedInController) context.get("LoggedInController")).viewUserInfoPage(ctx);});

        javalin.get("/Employees/UpdateUserInfo", ctx ->  {((LoggedInController) context.get("LoggedInController")).updateUserInfoPage(ctx);});
        javalin.post("/Employees/UpdateUserInfo", ctx -> {((EmployeeController) context.get("EmployeeController")).changePassword(ctx);});

        javalin.get("/Employees", ctx -> {ctx.render("/public/EmployeeLogin.html"); });
        javalin.post("/Employees", ctx -> {((EmployeeController) context.get("EmployeeController")).employeeLogin(ctx); });

        javalin.get("/EmployeeHome", ctx -> {((LoggedInController) context.get("LoggedInController")).employeeHomePage(ctx); });

        javalin.get("/Employees/NewReimbursement", ctx -> {((LoggedInController) context.get("LoggedInController")).newReimbursementPage(ctx);});
        javalin.post("/Employees/NewReimbursement", ctx -> {((ReimbursementController) context.get("ReimbursementController")).createReimbursement(ctx);});

        javalin.post("/ViewAllReimbursements", ctx -> {((ReimbursementController) context.get("ReimbursementController")).getEmployeeReimbursements(ctx);});
        javalin.get("/Employees/ViewAllReimbursements", ctx -> {((LoggedInController) context.get("LoggedInController")).viewAllReimbursementsPage(ctx);});
    }
}
