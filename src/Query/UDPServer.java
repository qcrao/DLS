package Query;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.List;

/**
 * Created by Administrator on 16/12/27.
 */
public class UDPServer
{
    public static final int PORT = 30000;
    private static final int DATA_LEN = 4096;
    byte[] inBuff = new byte[DATA_LEN];

    private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
    private DatagramPacket outPacket;


    public void init()
    {
        ShareData shareData = new ShareData();
        System.out.println("Preparing data...");
        shareData.getAll_port_port_journey();
        System.out.println("Data loaded!");
        ThroughputTonnageOfTwoNodes throughputTonnageOfTwoNodes = new ThroughputTonnageOfTwoNodes(shareData);
        TopNEdgesOfANode topNEdgesOfANode = new TopNEdgesOfANode(shareData);
        TopNEdges topNEdges = new TopNEdges(shareData);

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        while (true)
        {
        try {
                System.out.println("---------------start--------------------");
                System.out.println("System ready! waiting query...");
                socket.receive(inPacket);
                long startMili=System.currentTimeMillis();
                byte[] sendData = null;

                String query = new String(inBuff, 0, inPacket.getLength());
                System.out.println("Received query args: " + query);
                System.out.println("Start compute...");
                String[] args = query.split("~");

                try {
                    if (args[0].equals("Query_Arg_TwoNodes"))
                    {
                        //必须这么做，防止收到不完整的参数。如果采取设置的话，那不完全的参数会导致有些没有设置
                        Query_Arg_TwoNodes query_arg_twoNodes = new Query_Arg_TwoNodes(args);
                        String res = throughputTonnageOfTwoNodes.getStringRes(query_arg_twoNodes);
                        sendData = res.getBytes();
                    }

                    else if (args[0].equals("Query_Arg_TopNEdgesOfANode"))
                    {
                        Query_Arg_TopNEdgesOfANode query_arg_topNEdgesOfANode = new Query_Arg_TopNEdgesOfANode(args);
                        String res = topNEdgesOfANode.getStringRes(query_arg_topNEdgesOfANode);
                        sendData = res.getBytes();
                    }

                    else if (args[0].equals("Query_Arg_TopNEdges"))
                    {
                        Query_Arg_TopNEdges query_arg_topNEdges = new Query_Arg_TopNEdges(args);
                        String res = topNEdges.getStringRes(query_arg_topNEdges);
                        sendData = res.getBytes();
                    }

                }catch (Exception e){

                    e.printStackTrace();
                    System.err.println("ARGS ERROR!");

                    sendData = "ARGS ERROR!".getBytes();
                }
                outPacket = new DatagramPacket(sendData, sendData.length, inPacket.getSocketAddress());
                socket.send(outPacket);
                System.out.println("Compute finished!");
                System.out.println("Elapsed："+(System.currentTimeMillis()-startMili)+"ms");


        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    }

    public static void main(String[] args) {
        new UDPServer().init();
    }

}
