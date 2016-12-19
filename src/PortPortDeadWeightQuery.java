import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by qcrao on 16/12/12.
 */
public class PortPortDeadWeightQuery {

    private List<Port_Port> port_ports;
    private PortPortDeadWeight portPortDeadWeight = new PortPortDeadWeight();

    public PortPortDeadWeightQuery(List<Port_Port> port_ports) {
        this.port_ports = port_ports;
        countDeadWeight();
    }

    private int getShipType(int ship_type_imo)
    {
        if (ship_type_imo/10 == 7) //货轮
            ship_type_imo = 0;
        else if (ship_type_imo/10 == 8) //油轮
            ship_type_imo = 1;
        return ship_type_imo;
    }

    private void countDeadWeight()
    {
        Calendar calendar = Calendar.getInstance();

        for (Port_Port port_port : port_ports)
        {
            calendar.setTimeInMillis((long)((long)port_port.getStart_Datetime()*1000));
            int src_month = calendar.get(Calendar.MONTH)+1; //1-12

            calendar.setTimeInMillis((long)((long)port_port.getEnd_Datetime()*1000));
            int dst_month = calendar.get(Calendar.MONTH)+1;

            int ship_type = getShipType(port_port.getShip_Type_IMO());

            CountUnit_PortMonthShipType countUnit_portMonthShipType = new CountUnit_PortMonthShipType(port_port.getWorld_port_index_number_src(),
                                                                        port_port.getWorld_port_index_number_dst(), src_month, dst_month, ship_type);
            portPortDeadWeight.count(countUnit_portMonthShipType, port_port.getShip_Dead_Weight_Tonnage());

        }

    }

    public PortPortDeadWeight getRes()
    {
        return portPortDeadWeight;
    }
}
