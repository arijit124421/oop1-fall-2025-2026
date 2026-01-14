class Employee {
    private String employeeName;
    private Position position; 

    public Employee(String employeeName, Position position) {
        this.employeeName = employeeName;
        this.position = position;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Position getPosition() {
        return position;
    }
}

class Position {
    private String title;
    private double salary;

    public Position(String title, double salary) {
        this.title = title;
        this.salary = salary;
    }

    public String getTitle() {
        return title;
    }

    public double getSalary() {
        return salary;
    }
}

class Company {
    private String name;
    private Employee[] employees; 
    private int employeeCount = 0;

    public Company(String name, int maxEmployees) {
        this.name = name;
        this.employees = new Employee[maxEmployees];
    }

    public String getName() {
        return name;
    }

    public void addEmployee(Employee employee) {
        if (employeeCount < employees.length) {
            employees[employeeCount] = employee;
            employeeCount++;
        } else {
            System.out.println("Cannot hire more employees at " + name);
        }
    }

    public void showEmployees() {
        System.out.println("Company: " + name);
        System.out.println("Has " + employeeCount + " employee(s):");
        for (int i = 0; i < employeeCount; i++) {
            Employee e = employees[i];
            
            System.out.println(" - " + e.getEmployeeName() + 
                             " (Title: " + e.getPosition().getTitle() +  
                             ", Salary: $" + e.getPosition().getSalary() + ")");
        }
    }
}

public class AssociationTask2{
    public static void main(String[] args) {
        System.out.println("COMPANY WITH MULTIPLE EMPLOYEES:");
        
       
        Company company = new Company("NESTLE", 4);
        
       
        Position position1 = new Position("Software Engineer", 85000);
        Position position2 = new Position("HR Specialist", 65000);
        Position position3 = new Position("CEO", 150000);
        
        
        Employee employee1 = new Employee(" Han", position1);
        Employee employee2 = new Employee(" Bob ", position2);
        Employee employee3 = new Employee(" Sam ", position3);
        
        
        company.addEmployee(employee1);
        company.addEmployee(employee2);
        company.addEmployee(employee3);
        
        System.out.println();
        
        
        company.showEmployees();
    }
}







































