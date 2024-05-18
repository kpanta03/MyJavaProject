package hospitalinformationmanagement.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

public class HospitalInformationManagement {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "*****************";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }


        Scanner scanner = new Scanner(System.in);

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);
            while(true){
                System.out.println("+---------------------------------+");
                System.out.println("| HOSPITAL INFORMATION  MANAGEMENT |");
                System.out.println("+---------------------------------+");
                System.out.println("| 1. Insert Patient                |");
                System.out.println("| 2. Display Patients              |");
                System.out.println("| 3. Search Patient                |");
                System.out.println("| 4. Display Doctors               |");
                System.out.println("| 5. Search Doctor                 |");
                System.out.println("| 6. Book Appointment              |");
                System.out.println("| 7. Show Appointments             |");
                System.out.println("| 8. Exit                          |");
                System.out.println("+---------------------------------+");

                
                System.out.print("\nEnter your choice: ");
                int choice = scanner.nextInt();
                System.out.println();

                switch(choice){
                    case 1:
                        // Add Patient
                        patient.insertPatient();
                        System.out.println("\n\n");
                        break;

                    case 2:
                        // View Patient
                        patient.displayPatients();
                        System.out.println("\n\n");
                        break;
        
                    case 3:
                        // Search Patient
                        System.out.print("Enter Patient ID to search: ");
                        int patientIdToSearch = scanner.nextInt();
                        patient.searchPatient(patientIdToSearch);
                        System.out.println("\n\n");
                        break;

                    case 4:
                        // View Doctors
                        doctor.displayDoctors();
                        System.out.println("\n\n");
                        break;


                    case 5:
                        // Search Doctor
                        System.out.print("Enter Doctor ID to search: ");
                        int doctorIdToSearch = scanner.nextInt();
                        doctor.searchDoctor(doctorIdToSearch);
                        System.out.println("\n\n");
                        break;

                    case 6:
                        // Book Appointment
                        appointment(patient, doctor, connection, scanner);
                        System.out.println("\n\n");
                        break;

                    case 7:
                    displayAppointments(connection);
                    System.out.println();
                    break;


                    case 8:
                        System.out.println("THANK YOU! FOR USING OUR SERVICE!!");
                        return;

                    default:
                        System.out.println("Invalid choice! Enter valid choice!!!");
                        break;
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    
//appointment book garni
    public static void appointment(Patient patient, Doctor doctor, Connection connect, Scanner scanner) {

        System.out.print("Enter Id of patient: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Id of doctor: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();

        try {
            Statement statement = connect.createStatement();
            String availabilityQuery = "SELECT COUNT(*) FROM appointments WHERE doctor_id = " + doctorId +
                    " AND appointment_date = '" + appointmentDate + "'";
            ResultSet availabilityResult = statement.executeQuery(availabilityQuery);

            if (availabilityResult.next()) {
                int count = availabilityResult.getInt(1);
                if (count == 0) {
                    String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) " +
                            "VALUES(" + patientId + ", " + doctorId + ", '" + appointmentDate + "')";
                    int rowsAffected = statement.executeUpdate(appointmentQuery);
                    if (rowsAffected > 0) {
                        System.out.println("Appointment Booked!");
                    } else {
                        System.out.println("Failed to Book Appointment!");
                    }
                } else {
                    System.out.println("Doctor is not available on this date!!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    //sabai appointments display garni
    public static void displayAppointments(Connection connect) {
        try {
            Statement statement = connect.createStatement();
            String query = "SELECT * FROM appointments";
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("\t\t\tAppointments ");
            System.out.println("+-------------------------------------------------------+");
            System.out.println("| Appointment ID | Patient ID | Doctor ID |   Date      |");
            System.out.println("+-------------------------------------------------------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int patient_id = resultSet.getInt("patient_id");
                int doctor_id = resultSet.getInt("doctor_id");
                String appointment_date = resultSet.getString("appointment_date");

                System.out.printf("* %-14s | %-10s | %-9s | %-8s  *\n", id, patient_id, doctor_id, appointment_date);
           System.out.println("+-------------------------------------------------------+");
            }

            //System.out.println("*********************************************************");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    


}
