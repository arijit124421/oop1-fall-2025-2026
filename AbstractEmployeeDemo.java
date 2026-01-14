package UniversityLab;

abstract class Staff {

    String staffName;

    Staff(String staffName) {
        this.staffName = staffName;
    }

    abstract void computeSalary();

    void displayInfo() {
        System.out.println("Staff Name: " + staffName);
    }
}

class PermanentStaff extends Staff {

    double basicSalary;

    PermanentStaff(String staffName, double basicSalary) {
        super(staffName);
        this.basicSalary = basicSalary;
    }

    @Override
    void displayInfo() {
        System.out.println("Permanent Staff Details:");
        System.out.println("Name: " + staffName);
        System.out.println("Monthly Salary: $" + basicSalary);
    }

    @Override
    void computeSalary() {
        double yearlySalary = basicSalary * 12;
        System.out.println("Yearly Salary: $" + yearlySalary);
    }
}

public class AbstractStaffTest {
    public static void main(String[] args) {

        Staff staffObj = new PermanentStaff("Alex", 4500.0);

        staffObj.displayInfo();
        staffObj.computeSalary();
    }
}
