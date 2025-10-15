import java.sql.*;
import java.util.*;

public class Main {

    private static final String url = "jdbc:mysql://localhost:3306/students";
    private static final String username = "root";
    private static final String password = "yourpassword";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int attempts = 3;
        while(attempts > 0){
            System.out.println("Enter the password");
            String inputPassword = sc.next();

            if(inputPassword.equals(password)){
                System.out.println("Access Granted. \n");
                break;
            }else{
                attempts--;
                System.out.println("Wrong password "+attempts+" more attempts left");
                if(attempts == 0){
                    System.out.println("Too many failed attempts. Exiting Program");
                    System.exit(0);
                }
            }
        }

        while(true){
            System.out.println("\n===== Welcome to Student Management System =====");
            System.out.println("Please Enter your choice from below");
            System.out.println("1. Add a New Student");
            System.out.println("2. View All Students Records");
            System.out.println("3. Update a Student Record");
            System.out.println("4. Delete a Student Record");
            System.out.println("5. Fetch Details of a Student");
            System.out.println("6. Fetch Total Number of Students");
            System.out.println("7. Exit");

            int choice = sc.nextInt();

            switch (choice){
                case 1 -> addStudent(sc);
                case 2 -> viewStudents();
                case 3 -> updateStudent(sc);
                case 4 -> deleteStudent(sc);
                case 5 -> findStudent(sc);
                case 6 -> fetchTotal();
                case 7 -> {
                    System.out.println("Exiting the console.....");
                    System.exit(0);
                }
            }
        }
    }

    // 1
    static void addStudent(Scanner sc){
        System.out.println("Enter the details of the student:");
        System.out.println("Enter name: ");
        String name = sc.next();
        System.out.println("Enter age: ");
        int age = sc.nextInt();
        System.out.println("Enter email: ");
        String email = sc.next();
        System.out.println("Enter phone number: ");
        String phone = sc.next();
        System.out.println("Enter branch: ");
        String branch = sc.next();

        try(Connection con = DriverManager.getConnection(url, username, password)){
            String sql = "INSERT INTO StudentData(name, age, email, phone, branch) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setInt(2,age);
            ps.setString(3,email);
            ps.setString(4,phone);
            ps.setString(5,branch);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // 2
    static void viewStudents(){
        try(Connection con = DriverManager.getConnection(url, username,password)){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM StudentData");
            System.out.println("ID | Name | Age | Email | Phone | Branch");
            while(rs.next()){
                System.out.println(rs.getInt("id")+
                        " | " + rs.getString("name")+
                        " | " + rs.getInt("age")+
                        " | " + rs.getString("email")+
                        " | " + rs.getString("phone")+
                        " | " + rs.getString("branch"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //3
    static void updateStudent(Scanner sc){
        System.out.println("Enter the ID of student to update");
        int id = sc.nextInt();
        System.out.println("Which field do you want to update?");
        System.out.println("1. Name");
        System.out.println("2. Age");
        System.out.println("3. Email");
        System.out.println("4. Phone");
        System.out.println("5. Branch");
        int choice = sc.nextInt();

        String field = "";
        String newValue = "";
        int newIntValue = 0;

        switch (choice){
            case 1 -> {
                field = "name";
                System.out.println("Enter new name");
                newValue = sc.next();
            }
            case 2 -> {
                field = "age";
                System.out.println("Enter new age");
                newIntValue = sc.nextInt();
            }
            case 3 -> {
                field = "email";
                System.out.println("Enter new email");
                newValue = sc.next();
            }
            case 4 -> {
                field = "phone";
                System.out.println("Enter new phone number");
                newValue = sc.next();
            }
            case 5 -> {
                field = "branch";
                System.out.println("Enter new branch");
                newValue = sc.next();
            }
            default -> {
                System.out.println("Invalid Choice");
                return;
            }
        }

        try(Connection con = DriverManager.getConnection(url, username, password)){
            String sql = "UPDATE StudentData SET "+field+" =? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            if(field.equals("age")){
                ps.setInt(1,newIntValue);
            }else{
                ps.setString(1,newValue);
            }
            ps.setInt(2,id);

            int rowsUpdated = ps.executeUpdate();

            if(rowsUpdated > 0){
                System.out.println("Details of student with id:"+ id+" successfully Updated!");
            }else{
                System.out.println("Invalid ID - No student found with id: "+id);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // 4
    static void deleteStudent(Scanner sc){
        System.out.println("Enter the ID of the student u want to delete");
        int id = sc.nextInt();

        try(Connection con = DriverManager.getConnection(url, username, password)){
            String sql = "DELETE FROM StudentData WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Record deleted successfully!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // 5

    static void findStudent(Scanner sc){
        System.out.println("Enter the field based on which u want to find the student details: ");
        System.out.println("1. ID");
        System.out.println("2. Email");
        System.out.println("3. Phone");
        int choice = sc.nextInt();

        String field = "";
        int num = 0;
        String value = "";

        switch (choice){
            case 1 -> {
                field = "id";
                System.out.println("Enter the ID: ");
                num = sc.nextInt();
            }
            case 2 -> {
                field = "email";
                System.out.println("Enter the Email: ");
                value = sc.next();
            }
            case 3 -> {
                field = "phone";
                System.out.println("Enter the Phone Number: ");
                value = sc.next();
            }
            default -> {
                System.out.println("Invalid Choice");
            }
        }

        try(Connection con = DriverManager.getConnection(url, username, password)){
            String sql = "SELECT * FROM StudentData WHERE "+field+" =?";
            PreparedStatement ps = con.prepareStatement(sql);
            if(value.equals("id")){
                ps.setInt(1,num);
            }else{
                ps.setString(1,value);
            }
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("Student details fetched successfully!");
                System.out.println("ID | Name | Age | Email | Phone | Branch");
                System.out.println(rs.getInt("id")+
                        "|"+rs.getString("name")+
                        "|"+rs.getInt("age")+
                        "|"+rs.getString("email")+
                        "|"+rs.getString("phone")+
                        "|"+rs.getString("branch"));
            }else{
                System.out.println("Invalid "+field+" provided!");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    static void fetchTotal(){
        try(Connection con = DriverManager.getConnection(url, username, password)){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS Total FROM StudentData");
            while(rs.next()){
                int count = rs.getInt("Total");

                System.out.println("Total Number of students: "+count);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
