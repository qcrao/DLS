package Query;

import java.util.HashMap;

/**
 * Created by qcrao.
 * Email: qcrao@foxmail.com
 * Date: 17/1/5
 * Time: 9:22
 */
public class Port_Dictionary {

    static HashMap<String, String> mp_countryCode_name = Wpi_Country_DataGetter.getMp_countryCode_name();
    static HashMap<Integer, String> mp_regionId_name = Wpi_region_DataGetter.getMp_regionId_name();
    static HashMap<Integer, Wpi_Port> mp_id_port = Wpi_Port_DataGetter.getMp_id_port();

    static String getPortInfo_Res(int id)
    {
        Wpi_Port wpi_port = mp_id_port.get(id);
        String Country_name = mp_countryCode_name.get(wpi_port.getWpi_country_code());
        String region_name = mp_regionId_name.get(wpi_port.getRegion_index());
        return id + "~" + wpi_port.getName() + "~" + wpi_port.getWpi_country_code() + "~" + Country_name + "~" + wpi_port.getRegion_index() + "~" +
                region_name + "~" + wpi_port.getLongitude() + "~" + wpi_port.getLatitude();

    }

    static String getRegionInfo_Res(int id)
    {
        String region_name = mp_regionId_name.get(id);
        return id + "~" + region_name;
    }

    static String getCountryInfo_Res(String code)
    {
        return code + "~" + mp_countryCode_name.get(code);
    }

}
