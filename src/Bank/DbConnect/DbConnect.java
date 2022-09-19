package Bank.DbConnect;

import java.sql.*;

public class DbConnect {
    private Connection connection = null;

    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    private static final String DB_URL = "";

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            //statement = connection.createStatement();
            System.out.println("conected to db");
        } catch (SQLException e) {
            System.err.print(e.getMessage());
        } catch (Exception e) {
            System.err.print(e.getMessage() + "");
        }
    }

    public int execute(String sentence){
        try{
            PreparedStatement pstm = connection.prepareStatement(sentence);
            pstm.execute();
            return 1;
        } catch(SQLException e) {
            System.out.println(e);
            return 0;
        }
    }

    public ResultSet get(String sentence){
        try {
            PreparedStatement pstm = connection.prepareStatement(sentence);
            return pstm.executeQuery(sentence);
        } catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }
}
