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
    private static final String password = "Kritikasqlaccount";
    public static void main(String[] args) {
        clearScreen();
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
            while (true) {
                heading();
                System.out.print("\033[38;2;0;100;0m");

                System.out.println("\n\n\t\t\t\t+---------------------------------+");
                System.out.println("\t\t\t\t| HOSPITAL INFORMATION  MANAGEMENT |");
                System.out.println("\t\t\t\t+---------------------------------+");
                System.out.println("\t\t\t\t| 1. Patient                       |");
                System.out.println("\t\t\t\t| 2. Doctor                        |");
                System.out.println("\t\t\t\t| 3. Appointment                   |");
                System.out.println("\t\t\t\t| 4. Exit                          |");
                System.out.println("\t\t\t\t+---------------------------------+");

                System.out.print("\n\n\t\tEnter your choice: ");
                int mainChoice = scanner.nextInt();
                clearScreen();

                switch (mainChoice) {
                    case 1:
                    heading();
                    System.out.print("\033[38;2;0;100;0m");
                    System.out.print(
                        "\t\t\t\t+--------------------------+\n"+
                        "\t\t\t\t|     Patient Information  |\n"+
                        "\t\t\t\t+----+---------------------+\n" +
                        "\t\t\t\t| 1    Insert Patient      |\n" +
                        "\t\t\t\t| 2    Display Patients    |\n" +
                        "\t\t\t\t| 3    Search Patient      |\n" +
                        "\t\t\t\t+----+---------------------+\n"
                    );

                        System.out.print("\n\n\t\t\tEnter your choice: ");
                        int patientChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (patientChoice) {
                            case 1:
                                clearScreen();
                                heading();
                                System.out.print("\033[38;2;0;100;0m");
                                patient.insertPatient();
                        
                                
                                break;
                            case 2:
                                clearScreen();
                                heading();
                                System.out.print("\033[38;2;0;100;0m");
                                patient.displayPatients();
                                break;

                            case 3:
                                clearScreen();
                                heading();
                                System.out.print("\033[38;2;0;100;0m");
                                System.out.print("\n\n\t\tEnter Patient ID to search: ");
                                int patientIdToSearch = scanner.nextInt();
                                scanner.nextLine();
                                patient.searchPatient(patientIdToSearch);
                                break;
                            default:
                                System.out.print("\033[38;2;0;100;0m");
                                System.out.println("\n\tInvalid choice! Enter a valid choice!!!");
                                break;
                        }
                        scanner.nextLine();
                        clearScreen();
                        break;

                    case 2:
                    heading();
                    System.out.print("\033[38;2;0;100;0m");
                    System.out.print(
                        "\n\n\t\t\t\t+--------------------------+\n" +
                        "\t\t\t\t|  Doctor Information        |\n"+
                        "\t\t\t\t+----+---------------------+\n" +
                        "\t\t\t\t| 1    Display Doctors      |\n" +
                        "\t\t\t\t| 2    Search Doctor        |\n" +
                        "\t\t\t\t+----+---------------------+\n"
                    );
                        System.out.print("\n\n\t\t\tEnter your choice: ");
                        int doctorChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (doctorChoice) {
                            case 1:
                                clearScreen();
                                heading();
                                System.out.print("\033[38;2;0;100;0m");
                                doctor.displayDoctors();
                                break;
                            case 2:
                                clearScreen();
                                heading();
                                System.out.print("\033[38;2;0;100;0m");
                                System.out.print("\n\n\t\tEnter Doctor ID to search: ");
                                int doctorIdToSearch = scanner.nextInt();
                                scanner.nextLine();
                                doctor.searchDoctor(doctorIdToSearch);
                                break;
                            default:
                            System.out.print("\033[38;2;0;100;0m");
                                System.out.println("\n\tInvalid choice! Enter a valid choice!!!");
                                break;
                        }
                        scanner.nextLine();
                        clearScreen();
                        break;

                    case 3:
                    heading();
                    System.out.print("\033[38;2;0;100;0m");
                    System.out.print(
                        "\n\n\t\t\t\t+--------------------------+\n" +
                        "\t\t\t\t|        Appointments       |\n"+
                        "\t\t\t\t+----+---------------------+\n" +
                        "\t\t\t\t| 1  | Insert Appointment   |\n" +
                        "\t\t\t\t| 2  | Display Appointments |\n" +
                        "\t\t\t\t+----+---------------------+\n"
                    );
                        System.out.print("\n\n\t\t\tEnter your choice: ");
                        int appointmentChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (appointmentChoice) {
                            case 1:
                                clearScreen();
                                heading();
                                System.out.print("\033[38;2;0;100;0m");
                                appointment(patient, doctor, connection, scanner);
                                scanner.nextLine();
                                break;
                            case 2:
                                clearScreen();
                                heading();
                                System.out.print("\033[38;2;0;100;0m");
                                displayAppointments(connection);                           
                                break;
                            default:
                            System.out.print("\033[38;2;0;100;0m");
                                System.out.println("\n\tInvalid choice! Enter a valid choice!!!");
                                break;
                        }

                        scanner.nextLine();
                        clearScreen();
                        break;

                    case 4:
                        heading();
                        System.out.print("\033[38;2;0;100;0m");
                        System.out.println("\n\n\t\t\tTHANK YOU! FOR USING OUR SERVICE!!\n\n");
                        scanner.nextLine();
                        return;

                    default:
                    System.out.print("\033[38;2;0;100;0m");
                        System.out.println("\n\tInvalid choice! Enter a valid choice!!!");
                        break;
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void appointment(Patient patient, Doctor doctor, Connection connect, Scanner scanner) {

        System.out.print("\n\t\t\t\tEnter Id of patient: ");
        int patientId = scanner.nextInt();
        System.out.print("\t\t\t\tEnter Id of doctor: ");
        int doctorId = scanner.nextInt();
        System.out.print("\t\t\t\tEnter appointment date (YYYY-MM-DD): ");
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
                        System.out.println("\t\t\tAppointment Booked!");
                    } else {
                        System.out.println("\t\t\tFailed to Book Appointment!");
                    }
                } else {
                    System.out.println("\t\t\tDoctor is not available on this date!!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //
    public static void displayAppointments(Connection connect) {
        try {
            Statement statement = connect.createStatement();
            String query = "SELECT * FROM appointments";
            ResultSet resultSet = statement.executeQuery(query);
            

            System.out.println("\n\n\t\t\t\t\t\tAppointments ");
            System.out.println("\t\t\t+-------------------------------------------------------+");
            System.out.println("\t\t\t| Appointment ID | Patient ID | Doctor ID |   Date      |");
            System.out.println("\t\t\t+-------------------------------------------------------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int patient_id = resultSet.getInt("patient_id");
                int doctor_id = resultSet.getInt("doctor_id");
                String appointment_date = resultSet.getString("appointment_date");

                System.out.printf("\t\t\t| %-14s | %-10s | %-9s | %-8s  |\n", id, patient_id, doctor_id, appointment_date);
            System.out.println("\t\t\t+-------------------------------------------------------+");
            }

            //System.out.println("*********************************************************");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

public static void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape codes to clear screen
        System.out.flush();
    
    }

public static void heading(){
    System.out.print("\033[38;2;255;0;0m");
    String asciiArt = 
            "\n\n\n\n\n"+
            "\t\t   _  _        _    _                   _      _  _                 _  _          _ \n" +
            "\t\t  | \\| | __ _ | |_ (_) ___  _ _   __ _ | |    | || | ___  ___ _ __ (_)| |_  __ _ | |\n" +
            "\t\t  | .` |/ _` ||  _|| |/ _ \\| ' \\ / _` || |    | __ |/ _ \\(_-<| '_ \\| ||  _|/ _` || |\n" +
            "\t\t  |_|\\_|\\__,_| \\__||_|\\___/|_|\\_|\\__,_||_|    |_||_|\\___//__/| .__/|_| \\__|\\__,_||_|\n" +
            "\t\t                                                             |_|                     ";
           System.out.println(asciiArt);
           System.out.print("\033[0m");
    }
    }
