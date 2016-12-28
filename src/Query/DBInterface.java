package Query;

import java.sql.*;

/**
 * Created by qcrao on 16/10/31.
 */

public class DBInterface {

    private static Connection con = null;

    private static String ip = "127.0.0.1";
    private static String port = "3306";
    private static String db_name = "root";
    private static String db_password = "qazwsx";
    private static String db_schema = "dls";

    private static DBInterface instance = new DBInterface(ip, port, db_name, db_password, db_schema);

    public static DBInterface getInstance()
    {
        return instance;
    }


    private DBInterface(String ip, String port, String db_name, String db_password, String db_schema) {
        this.ip = ip;
        this.port = port;
        this.db_name = db_name;
        this.db_password = db_password;
        this.db_schema = db_schema;

        ConnectToDB();
    }

    public Connection getConnection()
    {
        return con;
    }

    public boolean ConnectToDB()
    {
        try {
            con = dbConn(db_name, db_password, db_schema);

            if (con == null)
            {
                System.out.println("Mysql connect Fail!");
                System.exit(0);
            }

            else System.out.println("Mysql connect Succeed!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    private Connection dbConn(String name, String pass, String schema)
    {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://" + ip +":" + port + "/"+ schema, name, pass);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return con;
    }

    public void Commit()
    {
        try {
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Close()
    {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String sql)
    {
        ResultSet result = null;
        try {
            Statement stmt = con.createStatement();
            result = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void excute(String sql)
    {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
