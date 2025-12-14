package Lab;

class Position {
    String title;
    double salary;

    Position(String title, double salary) {
        this.title = title;
        this.salary = salary;
    }

    void showPosition() {
        System.out.println("Position: " + title + ", Salary: " + salary);
    }
}

// Employee class
class Employee {
    String name;
    Position position;

    Employee(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    void showEmployee() {
        System.out.print("Employee Name: " + name + ", ");
        position.showPosition();
    }
}

// Company class
class Company {
    String name;
    Employee[] employees;
    int count = 0;

    Company(String name, int size) {
        this.name = name;
        employees = new Employee[size]; // fixed-size array
    }

    void addEmployee(Employee e) {
        employees[count++] = e;
    }

    void showAllEmployees() {
        System.out.println("Company: " + name);
        for (int i = 0; i < count; i++) {
            employees[i].showEmployee();
        }
    }
}

public class FinalTask2 {
    public static void main(String[] args) {

        Position manager = new Position("Manager", 80000);
        Position developer = new Position("Developer", 60000);
        Position intern = new Position("Intern", 20000);

        Employee e1 = new Employee("Alice", manager);
        Employee e2 = new Employee("Bob", developer);
        Employee e3 = new Employee("Charlie", intern);

        Company company = new Company("TechCorp", 5);
        company.addEmployee(e1);
        company.addEmployee(e2);
        company.addEmployee(e3);

        company.showAllEmployees();
    }
}