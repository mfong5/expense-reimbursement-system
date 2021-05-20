package pages;

import accounts.Reimbursements;
import controllers.EmployeeController;
import controllers.LoggedInController;
import controllers.ReimbursementController;
import io.javalin.Javalin;
import controllers.ManagerController;
import org.eclipse.jetty.util.log.Log;

import java.util.HashMap;

/**
 * Class to hold the javalin methods for pages related to the manager
 */
public class ManagerPages implements Page {
    private Javalin javalin;
    private HashMap<String, Object> context;

    public ManagerPages(Javalin javalinApp, HashMap<String, Object> appContext) {
        javalin = javalinApp;
        context = appContext;
    }

    @Override
    public void doPage() {
        javalin.get("/Managers", ctx -> {ctx.render("/public/ManagerLogin.html"); });
        javalin.post("/Managers", ctx -> {((ManagerController) context.get("ManagerController")).managerLogin(ctx);});

        javalin.get("/ManagerHome", ctx -> {((LoggedInController) context.get("LoggedInController")).managerHomePage(ctx);});

        javalin.get("/Managers/ViewEmployees", ctx -> {((LoggedInController) context.get("LoggedInController")).viewAllEmployeesPage(ctx);});
        javalin.post("/ViewEmployees", ctx -> {((EmployeeController) context.get("EmployeeController")).getAllEmployees(ctx); });

        javalin.get("/Managers/ManageReimbursements", ctx -> {((LoggedInController) context.get("LoggedInController")).manageReimbursementsPage(ctx);});
        javalin.post("/Managers/ManageReimbursements", ctx -> {((ManagerController) context.get("ManagerController")).employeeSearch(ctx);});
        javalin.post("/ManageEmployeeReimbursements", ctx -> {((ReimbursementController) context.get("ReimbursementController")).managerGetEmployeeReimbursements(ctx);});
        javalin.get("/Managers/ManageReimbursements/EmployeeReimbursements", ctx -> {((LoggedInController) context.get("LoggedInController")).employeeReimbursementsPage(ctx);});
        javalin.post("/Managers/ManageReimbursements/EmployeeReimbursements", ctx -> {((ReimbursementController) context.get("ReimbursementController")).updateReimbursement(ctx);});

        javalin.post("ManageAllReimbursements", ctx -> {((ReimbursementController) context.get("ReimbursementController")).managerGetAllReimbursements(ctx);});
        javalin.get("/Managers/ManageReimbursements/AllReimbursements", ctx -> {((LoggedInController) context.get("LoggedInController")).managerGetAllReimbursementsPage(ctx);});
        javalin.post("/Managers/ManageReimbursements/AllReimbursements", ctx -> {((ReimbursementController) context.get("ReimbursementController")).updateReimbursementAll(ctx);});
    }
}
