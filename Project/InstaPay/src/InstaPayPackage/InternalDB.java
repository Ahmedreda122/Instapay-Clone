package InstaPayPackage;
import java.sql.*;
public class InternalDB {
    protected Connection connection;
    public InternalDB() throws SQLException {
        String jdbcUrl = "jdbc:sqlite:" + "instapay.db";
        this.connection = DriverManager.getConnection(jdbcUrl);
    }
    public boolean checkTypeUsingNumber(String mobilePhone, String tableName) throws SQLException{
        String query = "SELECT COUNT(*) AS count FROM  " + tableName  + " WHERE mobileNumber = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, mobilePhone);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                int count = res.getInt("count");
                return (count == 1);
            }
            return  false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public double retrieveBalance(String mobilePhone, String accountType) throws SQLException {
        String query = "SELECT balance FROM " + accountType + " WHERE mobileNumber = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, mobilePhone);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getDouble("balance");
        }
    }
    public boolean updateBalance(String tableName, double amount, String mobileNumber) throws SQLException{
        String query = "UPDATE " + tableName + " SET balance = balance + ? WHERE mobileNumber = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, mobileNumber);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
