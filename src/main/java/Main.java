import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД Установлено!");
            }
            connection.close();
            if (connection.isClosed()) {
                System.out.println("Соединение с БД Закрыто!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
