package Lab;

abstract class Employee {

    String name;

   
    Employee(String name) {
        this.name = name;
    }

    
    abstract void calculateSalary();

    
    void showDetails() {
        System.out.println("Employee Name: " + name);
    }
}


class FullTimeEmployee extends Employee {
    double monthlySalary;

    FullTimeEmployee(String name, double monthlySalary) {
        super(name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    void showDetails() {
        System.out.println("Full-Time Employee Details:");
        System.out.println("Name: " + name);
        System.out.println("Monthly Salary: $" + monthlySalary);
    }

    @Override
    void calculateSalary() {
        double annualSalary = monthlySalary * 12;
        System.out.println("Annual Salary: $" + annualSalary);
    }
}


public class AbstractEmployeeDemo {
    public static void main(String[] args) {

        Employee emp = new FullTimeEmployee("John ", 5000.0);

        emp.showDetails();
        emp.calculateSalary();
    }
}