package Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 16/12/20.
 */
public class ShareData {
    private static List<Port_Port_Journey> all_port_port_journeys = null;
    private DBInterface db = DBInterface.getInstance();
    private static final int ITEMS_PERTIME = 250000;
    private long items_totalNum = 0;

    private String tableName = "l2_port_port_journey_indian";
    // private String tableName = "tmp";

    private String rule = " where Ship_Dead_Weight_Tonnage > 0 and ClassType = 0 ";
    private String sql = "SELECT World_port_index_number_src,\n" +
            "    World_port_index_number_dst,\n" +
            "    Start_Datetime,\n" +
            "    End_Datetime,\n" +
            "    Ship_ID,\n" +
            "    ClassType,\n" +
            "    MMSI,\n" +
            "    Ship_Type_AIS,\n" +
            "    Ship_Type_IMO,\n" +
            "    Country_Code_Src,\n" +
            "    Country_Code_Dst,\n" +
            "    Region_Index_Src,\n" +
            "    Region_Index_Dst,\n" +
            "    Ship_Dead_Weight_Tonnage\n" +
            "FROM dls." + tableName + rule;

    private void getData()
    {
        Calendar calendar = Calendar.getInstance();

        String sql_itemNums = "select count(*) from " + tableName + rule;

        ResultSet res_itemNums = db.query(sql_itemNums);
        try {
            while (res_itemNums.next())
            {
                items_totalNum = res_itemNums.getLong(1);
                System.out.println("Loading " + items_totalNum + " rows data...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int offset = 0;
        while (offset < items_totalNum)
        {
            String do_sql =  sql + " limit " + Integer.toString(offset) + " , " + Long.toString(items_totalNum) + " ";
            ResultSet res = db.query(do_sql);
            try {
                while (res.next()) {
                    Port_Port_Journey port_port_journey = new Port_Port_Journey();
                    port_port_journey.setWorld_port_index_number_src(res.getInt(1));
                    port_port_journey.setWorld_port_index_number_dst(res.getInt(2));
                    port_port_journey.setStart_Datetime(res.getInt(3));
                    port_port_journey.setEnd_Datetime(res.getInt(4));
                    port_port_journey.setShip_ID(res.getInt(5));
                    port_port_journey.setClassType(res.getInt(6));
                    port_port_journey.setMMSI(res.getInt(7));
                    port_port_journey.setShipTypeAIS(res.getInt(8));
                    port_port_journey.setShip_Type_IMO(res.getInt(9));
                    port_port_journey.setCountry_Code_Src(res.getString(10));
                    port_port_journey.setCountry_Code_Dst(res.getString(11));
                    port_port_journey.setRegion_Index_Src(res.getInt(12));
                    port_port_journey.setRegion_Index_Dst(res.getInt(13));
                    port_port_journey.setShip_Dead_Weight_Tonnage(res.getInt(14));

                    port_port_journey.setAddedProperties(); //year, month, tradeType

                    all_port_port_journeys.add(port_port_journey);
                }
                res.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            offset += ITEMS_PERTIME;
        }
    }

    public List<Port_Port_Journey> getAll_port_port_journey()
    {
        if (all_port_port_journeys == null)
        {
            all_port_port_journeys = new ArrayList<Port_Port_Journey>((int)items_totalNum);
            //initialize the list
            getData();
        }
        return all_port_port_journeys;
    }
}
