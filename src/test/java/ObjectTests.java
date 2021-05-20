import accounts.Employees;
import accounts.Managers;
import accounts.Reimbursements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectTests {

    @Test
    public void employeeNoParamConstructorTest() {
        Employees testEmployee = new Employees();
        String username = testEmployee.getUsername();
        String password = testEmployee.getPassword();
        Assertions.assertEquals("", username);
        Assertions.assertEquals("", password);
    }

    @Test
    public void employeeConstructorTest() {
        Employees testEmployee = new Employees("test", "test");
        Assertions.assertEquals("test", testEmployee.getUsername());
        Assertions.assertEquals("test", testEmployee.getPassword());
    }

    @Test
    public void managerNoParamConstructorTest() {
        Managers testManager = new Managers();
        String username = testManager.getUsername();
        String password = testManager.getPassword();
        Assertions.assertEquals("", username);
        Assertions.assertEquals("", password);
    }

    @Test
    public void managerConstructorTest() {
        Managers testManager = new Managers("test", "test");
        Assertions.assertEquals("test", testManager.getUsername());
        Assertions.assertEquals("test", testManager.getPassword());
    }

    @Test
    public void employeeToString() {
        Employees testEmployee = new Employees("test", "test");
        String testString = "Username:test\nPassword:test";
        Assertions.assertEquals(testString, testEmployee.toString());
    }

    @Test
    public void managerToString() {
        Managers testManager = new Managers("test", "test");
        String testString = "Username:test\nPassword:test";
        Assertions.assertEquals(testString, testManager.toString());
    }

    @Test
    public void employeeSetVars() {
        Employees testEmployee = new Employees("test", "test");
        testEmployee.setUsername("testUser");
        testEmployee.setPassword("testPassword");
        Assertions.assertEquals("testUser", testEmployee.getUsername());
        Assertions.assertEquals("testPassword", testEmployee.getPassword());
    }

    @Test
    public void managerSetVars() {
        Managers testManager = new Managers("test", "test");
        testManager.setUsername("testUser");
        testManager.setPassword("testPassword");
        Assertions.assertEquals("testUser", testManager.getUsername());
        Assertions.assertEquals("testPassword", testManager.getPassword());
    }

    @Test
    public void reimbursementNoParamTest() {
        Reimbursements testReimbursement = new Reimbursements();
        Assertions.assertEquals("", testReimbursement.getEmployee());
        Assertions.assertEquals("", testReimbursement.getManager());
        Assertions.assertEquals("", testReimbursement.getReason());
        Assertions.assertEquals("", testReimbursement.getStatus());
        Assertions.assertEquals(0, testReimbursement.getExpense());
    }

    @Test
    public void reimbursementConstructorTest() {
        Reimbursements testReimbursement = new Reimbursements("Test", "Test", 0);
        Assertions.assertEquals("Test", testReimbursement.getEmployee());
        Assertions.assertEquals("", testReimbursement.getManager());
        Assertions.assertEquals("Test", testReimbursement.getReason());
        Assertions.assertEquals("Pending", testReimbursement.getStatus());
        Assertions.assertEquals(0, testReimbursement.getExpense());
    }

    @Test
    public void approvalAndDenialTest() {
        Reimbursements testReimbursement = new Reimbursements("Test", "Test", 0);
        testReimbursement.approveRequest("test");
        Assertions.assertEquals("Approved", testReimbursement.getStatus());
        testReimbursement.denyRequest("test");
        Assertions.assertEquals("Denied", testReimbursement.getStatus());
    }

    @Test
    public void reimbursementToString() {
        Reimbursements testReimbursement = new Reimbursements("Test", "Test", 0);
        String toString = testReimbursement.toString();
        String testString = "Employee:Test\nReason:Test\nExpense:0.0\nStatus:Pending\nManager:";
        Assertions.assertEquals(testString, toString);

    }
}
