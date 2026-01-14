class Student {
    private String studentName;
    private Contact contact;  

    public Student(String studentName, Contact contact) {
        this.studentName = studentName;
        this.contact = contact;
    }

    public String getStudentName() {
        return studentName;
    }

    public Contact getContact() {
        return contact;
    }
}

class Contact {
    private String phone;
    private String email;

    public Contact(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}

class School {
    private String name;
    private Student[] students;  
    private int studentCount = 0;

    public School(String name, int maxStudents) {
        this.name = name;
        this.students = new Student[maxStudents];
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student student) {
        if (studentCount < students.length) {
            students[studentCount] = student;
            studentCount++;
        } else {
            System.out.println("Cannot add more students to " + name);
        }
    }

    public void showStudents() {
        System.out.println("School: " + name);
        System.out.println("Has " + studentCount + " student(s):");
        for (int i = 0; i < studentCount; i++) {
            Student s = students[i];
            System.out.println(" - " + s.getStudentName() + 
                             " (Phone: " + s.getContact().getPhone() + 
                             ", Email: " + s.getContact().getEmail() + ")");
        }
    }
}

public class AssociationTask1 {
    public static void main(String[] args) {
        System.out.println("SCHOOL WITH MANY STUDENTS:");
        
        
        School school = new School("Greenherald", 3);
        
       
        Contact contact1 = new Contact("456-789", "john@email.com");
        Contact contact2 = new Contact("123-456", "sarah@email.com");
        Contact contact3 = new Contact("555-666", "mike@email.com");
        
        
        Student student1 = new Student("John", contact1);
        Student student2 = new Student("Sarah", contact2);
        Student student3 = new Student("Mike", contact3);
        
        
        school.addStudent(student1);
        school.addStudent(student2);
        school.addStudent(student3);
        
        System.out.println();
        
       
        school.showStudents();
    }
}
    
