import java.sql.*;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/atmdb";
    private static final String USER = "root";
    private static final String PASSWORD = "YOUR_MYSQL_PASSWORD";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Account login(int accountNumber, int pin) {
        try {
            Connection con = getConnection();

            String sql = "SELECT * FROM accounts WHERE account_number=? AND pin=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, accountNumber);
            ps.setInt(2, pin);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Account account = new Account(
                        rs.getInt("account_number"),
                        rs.getInt("pin"),
                        rs.getDouble("balance"));

                con.close();
                return account;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void updateBalance(Account account) {
        try {
            Connection con = getConnection();

            String sql = "UPDATE accounts SET balance=? WHERE account_number=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, account.getBalance());
            ps.setInt(2, account.getAccountNumber());

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}