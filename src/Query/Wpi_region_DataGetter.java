package Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by qcrao.
 * Email: qcrao@foxmail.com
 * Date: 17/1/4
 * Time: 16:33
 */
public class Wpi_region_DataGetter extends DataGetter {

    static String table_name = "wpi_region";
    private static HashMap<Integer, String> mp_regionId_name = null;

    private static String sql = "select Region_index, Region_name from " + table_name;

    private static void getData()
    {
        ResultSet res = db.query(sql);

        try {
            while (res.next())
            {
                int index = res.getInt(1);
                String name = res.getString(2);
                mp_regionId_name.put(index, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Integer, String> getMp_regionId_name() {
        if (mp_regionId_name == null)
        {
            mp_regionId_name = new HashMap<>();
            getData();
        }
        return mp_regionId_name;
    }

    public static void main(String[] args) {
        Wpi_region_DataGetter wpi_region_dataGetter = new Wpi_region_DataGetter();
        HashMap<Integer, String> mp = wpi_region_dataGetter.getMp_regionId_name();
        System.out.println(mp.size());
        System.out.println(mp.get(545));
    }
}
