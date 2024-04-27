package hospitalinformationmanagement.src;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Scanner;
import java.sql.Statement;


//class for doctor
public class Doctor {
    private Connection connect;
    //private Scanner scanner;

    public Doctor(Connection connect){
        this.connect = connect;
       // this.scanner = scanner;
    }


    //display information of all doctors
    public void displayDoctors() {
        String query = "SELECT * FROM doctors";
        try (Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("\t\t\tDoctors ");
            System.out.println("╔════════════╦════════════════════╦═════════════════╗");
            System.out.println("║ Doctor Id  ║ Name               ║ Department      ║");
            System.out.println("╠════════════╬════════════════════╬═════════════════╣");
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                
                System.out.printf("║ %-10s ║ %-18s ║ %-16s║\n", id, name, department);
                System.out.println("╠════════════╬════════════════════╬════════════════ ╣");
            }
            
            System.out.println("╚════════════╩════════════════════╩═════════════════╝");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




//searching doctor by their id
public void searchDoctor(int id) {
    try {
        Statement statement = connect.createStatement();
        String query = "SELECT * FROM doctors WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            // Doctor with the specified ID exists
            System.out.println("Doctor found!!\n");
            
            int doctorId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String department = resultSet.getString("department");

            System.out.println("Doctor ID: " + doctorId);
            System.out.println("Name: " + name);
            System.out.println("Department: " + department);
        } else {
            System.out.println("Doctor not found!!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
