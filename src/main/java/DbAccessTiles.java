import java.math.BigDecimal;
import java.sql.*;

public class DbAccessTiles {

    static double getPrice(String model) {
        double price = 0;
        try {
            final String URL = "jdbc:sqlserver://localhost\\MSSQLSERVER17_CI:57935";
            final String USERNAME = "sa";
            final String PASSWORD = "ok";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //noinspection SqlResolve
            PreparedStatement myStmt = conn.prepareStatement("SELECT CAST(price_sqm as numeric(5,2)) price_sqm FROM Tiles.dbo.tiles WHERE model=?");

            myStmt.setString(1, model);
            ResultSet myRs = myStmt.executeQuery();
            while (myRs.next()) {
              //  System.out.println("myRs " + myRs.getDouble("price_sqm"));
                price = myRs.getDouble("price_sqm");
            }
            myStmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return price;
    }
}
