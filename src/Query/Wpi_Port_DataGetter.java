package Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by qcrao.
 * Email: qcrao@foxmail.com
 * Date: 17/1/4
 * Time: 17:03
 */
public class Wpi_Port_DataGetter extends DataGetter {
    static String table_name = " my_wpi_ports ";
    private static HashMap<Integer, Wpi_Port> mp_id_port = null;

    private static String sql = "select World_port_index_number, Region_index, Main_port_name, Wpi_country_code, Longitude, Latitude from  " + table_name;

    private static void getData()
    {
        ResultSet res = db.query(sql);

        try {
            while (res.next())
            {
                int idex = res.getInt(1);
                Wpi_Port wpi_port = new Wpi_Port();
                wpi_port.setId(idex);
                wpi_port.setIndex(res.getInt(1));
                wpi_port.setRegion_index(res.getInt(2));
                wpi_port.setName(res.getString(3));
                wpi_port.setWpi_country_code(res.getString(4));
                wpi_port.setLongitude(res.getInt(5));
                wpi_port.setLatitude(res.getInt(6));
                mp_id_port.put(idex, wpi_port);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Integer, Wpi_Port> getMp_id_port() {
        if (mp_id_port == null)
        {
            mp_id_port = new HashMap<>();
            getData();
        }
        return mp_id_port;
    }

    public static void main(String[] args) {
        Wpi_Port_DataGetter wpi_country_dataGetter = new Wpi_Port_DataGetter();
        HashMap<Integer, Wpi_Port> mp = wpi_country_dataGetter.getMp_id_port();
        System.out.println(mp.size());
        System.out.println(mp.get(160));
    }
}
