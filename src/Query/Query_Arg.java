package Query;

import javax.sound.sampled.Port;
import java.util.HashSet;

/**
 * Created by Administrator on 16/12/23.
 */
public abstract class Query_Arg implements Cloneable{
    int year = 0, month = 0;
    int aisShipType = 0, imoShipType = 0;
    int lowerThreshDWT = 0, tradeType = TRADE_BETWEEN_COUNTYR;
    int throughputType = IM_EXPORT;

    public static final int TRADE_IN_COUNTYR = 1;
    public static final int TRADE_BETWEEN_COUNTYR = 2;
    public static final int TRADE_ALL = 3;

    public static final int IMPORT = 1;
    public static final int EXPORT = 2;
    public static final int IM_EXPORT = 3;

    public static final int PORT = 1;
    public static final int COUNTRY = 2;
    public static final int REGION = 3;

    private HashSet<Integer> monthsToQuery = new HashSet<Integer>();

    protected void addMonths()
    {
        monthsToQuery.clear();
        if (month == 0)
        {
            for (int i = 1; i <= 12; ++i)
                monthsToQuery.add(i);
        }
        else if (!(month == 21 || month == 22 || month == 23 || month == 24))
            monthsToQuery.add(month);

        else if (month == 21 || month == 22 || month == 23 || month == 24)
        {
            for (int i = 1; i <= 3; ++i)
                monthsToQuery.add(3*(month-21)+i);
        }
        else
            System.err.println("Month error!");

    }

    @Override
    protected Query_Arg clone()  {
        //return super.clone();
        Query_Arg newQuery_Arg = null;
        try {
            newQuery_Arg = (Query_Arg) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newQuery_Arg;
    }

    private boolean checkSrcTime(Port_Port_Journey port_port_journey)
    {

        if (year != 0 && year != port_port_journey.getDst_year())
            return false;
        if (!monthsToQuery.contains(port_port_journey.getDst_month()))
            return false;
        return true;
    }

    private boolean checkDstTime(Port_Port_Journey port_port_journey)
    {
        if (year != 0 && year != port_port_journey.getSrc_year())
            return false;
        if (!monthsToQuery.contains(port_port_journey.getSrc_month()))
            return false;
        return true;
    }

    protected boolean checkTime(Port_Port_Journey port_port_journey)
    {
        if (throughputType == Query_Arg.IMPORT) //import
        {
            return checkSrcTime(port_port_journey);
        }

        else if (throughputType == Query_Arg.EXPORT) //export
        {
            return checkDstTime(port_port_journey);
        }
        else if (throughputType == Query_Arg.IM_EXPORT)
        {
            if (checkSrcTime(port_port_journey) || checkDstTime(port_port_journey))
                return true;
            return false;
        }
        else
            System.err.println("month input error!");
        return true;
    }

    protected boolean checkShipTypeIMO(Port_Port_Journey port_port_journey)
    {
        return true;
    }

    protected boolean checkShipTypeAIS(Port_Port_Journey port_port_journey)
    {
        if (aisShipType != 0 && aisShipType != port_port_journey.getShipTypeAIS()/10*10)
            return false;
        return true;
    }

    protected boolean checkTradeType(Port_Port_Journey port_port_journey)
    {
        if (tradeType != Query_Arg.TRADE_ALL && tradeType != port_port_journey.getTradeType())
            return false;
        return true;
    }

    protected boolean checkLowestDeadWeight(Port_Port_Journey port_port_journey)
    {
        if (port_port_journey.getShip_Dead_Weight_Tonnage() < lowerThreshDWT)
            return false;
        return true;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Query_Arg query_arg = (Query_Arg) o;

        if (year != query_arg.year) return false;
        if (month != query_arg.month) return false;
        if (aisShipType != query_arg.aisShipType) return false;
        if (imoShipType != query_arg.imoShipType) return false;
        if (lowerThreshDWT != query_arg.lowerThreshDWT) return false;
        if (tradeType != query_arg.tradeType) return false;
        return throughputType == query_arg.throughputType;
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + month;
        result = 31 * result + aisShipType;
        result = 31 * result + imoShipType;
        result = 31 * result + lowerThreshDWT;
        result = 31 * result + tradeType;
        result = 31 * result + throughputType;
        return result;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getAisShipType() {
        return aisShipType;
    }

    public void setAisShipType(int aisShipType) {
        this.aisShipType = aisShipType;
    }

    public int getImoShipType() {
        return imoShipType;
    }

    public void setImoShipType(int imoShipType) {
        this.imoShipType = imoShipType;
    }

    public int getLowerThreshDWT() {
        return lowerThreshDWT;
    }

    public void setLowerThreshDWT(int lowerThreshDWT) {
        this.lowerThreshDWT = lowerThreshDWT;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public int getThroughputType() {
        return throughputType;
    }

    public void setThroughputType(int throughputType) {
        this.throughputType = throughputType;
    }

    public Query_Arg(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public Query_Arg() {
    }
}
