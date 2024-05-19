package hospitalinformationmanagement.src;
import java.sql.Connection;
import java.sql.ResultSet;
//import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

public class Patient {
    private Connection connect;
    private Scanner scanner;
    public Patient(Connection connect, Scanner scanner){
        this.connect = connect;
        this.scanner = scanner;
    }
    //naya patient add garni
    public void insertPatient(){
        System.out.print("\n\n\t\t\t\tEnter name of patient:");
        String name = scanner.next();
        System.out.print("\t\t\t\tEnter age of patient: ");
        int age = scanner.nextInt();
        System.out.print("\t\t\t\tEnter Gender of patient:");
        String gender = scanner.next();
        //inserting into patient table  
        try{
            Statement statement = connect.createStatement();
            String query = "INSERT INTO patients(name, age, gender) " +
                           "VALUES('" + name + "', " + age + ", '" + gender + "')";
            int affectedRows = statement.executeUpdate(query);
            if(affectedRows > 0){
                System.out.println("\n\t\t\tPatient inserted Successfully!!");
            }else{
                System.out.println("\n\t\t\tFailed to insert Patient!!");
            }
        }catch (SQLException e){
            e.printStackTrace();//method of SQLExecution
        }
    }

//displaying information of all patients
    public void displayPatients(){
    String query = "SELECT * FROM patients";
    try{
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("\n\n\t\t\t\t\t\t\tPatients");
        System.out.println("\t\t\t\t╔════════════╦════════════════════╦══════════╦══════════╗");
        System.out.println("\t\t\t\t║ Patient Id ║ Name               ║ Age      ║ Gender   ║");
        System.out.println("\t\t\t\t╠════════════╬════════════════════╬══════════╬══════════╣");
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            String gender = resultSet.getString("gender");
            System.out.printf("\t\t\t\t║ %-10s ║ %-18s ║ %-8s ║ %-8s ║\n", id, name, age, gender);
            System.out.println("\t\t\t\t╠════════════╬════════════════════╬══════════╬══════════╣");
        }
        System.out.println("\t\t\t\t╚════════════╩════════════════════╩══════════╩══════════╝");

    }catch (SQLException e){
        e.printStackTrace();
    }
}

//searching patient by their id and displaying
public boolean searchPatient(int id) {
    try {
        Statement statement = connect.createStatement();
        String query = "SELECT * FROM patients WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            // Patient with the specified ID exists
            System.out.println("\n\t\t\tPatient found!!\n");
            int patientId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            String gender = resultSet.getString("gender");

            System.out.println("\n\n\t\t\t\tPatient ID: " + patientId);
            System.out.println("\t\t\t\tName: " + name);
            System.out.println("\t\t\t\tAge: " + age);
            System.out.println("\t\t\t\tGender: " + gender);
            
            return true;
        } else {
            // Patient not found
            System.out.println("\n\t\t\t\tPatient not found!\n");
            return false;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Error occurred or database connection issue
    }
}
}
