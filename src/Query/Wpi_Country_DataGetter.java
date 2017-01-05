package Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by qcrao.
 * Email: qcrao@foxmail.com
 * Date: 17/1/4
 * Time: 16:59
 */
public class Wpi_Country_DataGetter extends DataGetter {
    static String table_name = "my_country_codes";
    private static HashMap<String, String> mp_countryCode_name = null;

    private static String sql = "select Country_Code, Country_Name from " + table_name;

    private static void getData()
    {
        ResultSet res = db.query(sql);

        try {
            while (res.next())
            {
                String code = res.getString(1);
                String name = res.getString(2);
                mp_countryCode_name.put(code, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, String> getMp_countryCode_name() {
        if (mp_countryCode_name == null)
        {
            mp_countryCode_name = new HashMap<>();
            getData();
        }
        return mp_countryCode_name;
    }

    public static void main(String[] args) {
        Wpi_Country_DataGetter wpi_country_dataGetter = new Wpi_Country_DataGetter();
        HashMap<String, String> mp = wpi_country_dataGetter.getMp_countryCode_name();
        System.out.println(mp.size());
        System.out.println(mp.get("AG"));
    }
}
