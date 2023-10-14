package wedevx.digitalbank.automation.ui.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static wedevx.digitalbank.automation.ui.utils.ConfigReader.getPropertiesValue;

public class DBUtils {
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    //method to establish connection with the db
    public static void establishConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(ConfigReader.getPropertiesValue("digitalbank.db.url"),
                    getPropertiesValue("digitalbank.db.username"),
                    getPropertiesValue("digitalbank.db.password"));


        } catch (SQLException  |ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // a method that can dynamically send select statements
    //and returns a list of map of all columns
    public static List<Map<String, Object>> runSQLSelectQuery(String sqlQuery) {
        List<Map<String, Object>> dbResultList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            //getMetaData method return info about your info.
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                for (int col = 1; col <= columnCount; col++) {
                    rowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
                }
                dbResultList.add(rowMap);
            }
            return dbResultList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //create a method that inserts and update into db
    //return the nums of rows updated or 0 when action is not taken

    //delete or truncate the table
    //update query
    public static int runSQLUpdateQuery(String sqlQuery) {
        int rowsAffected = 0;
        try {
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    //close connection
    public static void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
