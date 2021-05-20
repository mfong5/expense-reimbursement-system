package controllers;

import io.javalin.http.Context;

/**
 * Controller to make sure that an employee or manager is currently logged in
 * in order to access the related pages
 */
public class LoggedInController {
    private EmployeeController employeeController;
    private ManagerController managerController;

    public LoggedInController(EmployeeController employeeController, ManagerController managerController) {
        this.employeeController = employeeController;
        this.managerController = managerController;
    }

    public void homePage(Context ctx) {
        employeeController.logOut();
        ctx.render("public/index.html");
    }

    public void employeeHomePage(Context ctx) {
        String check = employeeController.getCurrentUser();
        if (check == null) {
            ctx.redirect("/Employees");
        }

        else {
            ctx.render("/public/EmployeeHome.html");
        }
    }

    public void viewUserInfoPage(Context ctx) {
        String check = employeeController.getCurrentUser();
        if (check == null) {
            ctx.redirect("/Employees");
        }

        else {
            ctx.render("/public/ViewUserInfo.html");
        }
    }

    public void updateUserInfoPage(Context ctx) {
        String check = employeeController.getCurrentUser();
        if (check == null) {
            ctx.redirect("/Employees");
        }

        else {
            ctx.render("/public/UpdateUserInfo.html");
        }
    }

    public void newReimbursementPage(Context ctx) {
        String check = employeeController.getCurrentUser();
        if (check == null) {
            ctx.redirect("/Employees");
        }

        else {
            ctx.render("/public/newReimbursement.html");
        }
    }

    public void viewAllReimbursementsPage(Context ctx) {
        String check = employeeController.getCurrentUser();
        if (check == null) {
            ctx.redirect("/Employees");
        }

        else {
            ctx.render("/public/ViewAllReimbursements.html");
        }
    }

    public void managerHomePage(Context ctx) {
        String check = managerController.getCurrentUser();
        if (check == null) {
            ctx.redirect("/Managers");
        }

        else {
            ctx.render("/public/ManagerHome.html");
        }
    }

    public void viewAllEmployeesPage(Context ctx) {
        String check = managerController.getCurrentUser();
        if (check == null) {
            ctx.redirect("/Managers");
        }

        else {
            ctx.render("/public/ViewEmployees.html");
        }
    }

    public void manageReimbursementsPage(Context ctx) {
        String check = managerController.getCurrentUser();
        if (check == null) {
            ctx.redirect("/Managers");
        }

        else {
            ctx.render("/public/ManageReimbursements.html");
        }
    }

    public void employeeReimbursementsPage(Context ctx) {
        String check = managerController.getCurrentUser();
        if (check == null) {
            ctx.redirect("/Managers");
        }

        else {
            ctx.render("/public/ManageEmployeeReimbursement.html");
        }
    }

    public void managerGetAllReimbursementsPage(Context ctx) {
        String check = managerController.getCurrentUser();
        if (check == null) {
            ctx.redirect("/Managers");
        }

        else {
            ctx.render("/public/ManageAllReimbursements.html");
        }
    }
}
