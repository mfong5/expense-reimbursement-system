package accounts;

/**
 * Class for holding Reimbursement information as well as methods to either
 * approve or deny the reimbursement
 */
public class Reimbursements {
    private String employee;
    private String reason;
    private double expense;
    private String status;
    private String manager;

    public Reimbursements() {
        this.employee = "";
        this.reason = "";
        this.expense = 0;
        this.status = "";
        this.manager = "";
    }

    public Reimbursements(String employee, String reason, double expense) {
        this.employee = employee;
        this.reason = reason;
        this.expense = expense;
        this.status = "Pending";
        this.manager = "";
    }

    public void approveRequest(String manager) {
        this.status = "Approved";
        this.manager = manager;
    }

    public void denyRequest(String manager) {
        this.status = "Denied";
        this.manager = manager;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Employee:" + employee + "\nReason:" + reason + "\nExpense:" + expense +
        "\nStatus:" + status + "\nManager:" + manager;
    }
}
