import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/atmdb";
        String username = "root";
        String password = "devisri1259";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected Successfully!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}