import comm.Request;
import comm.Response;
import comm.Status;
import domain.Packet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 3333);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        //Receiving from server to client
        Object inObject = in.readObject();
        while (inObject == null) {
            inObject = in.readObject();
        }
        if (inObject instanceof Request) {
            Request request = (Request) inObject;
            List<Packet> packets = new ArrayList<>(request.getPackets());
            Response response = new Response(1, Status.OK);
            out.writeObject(response);
            out.flush();
            System.out.println("Client : acceptance response for server sent " + response);
            for (int i = 0; i < request.getPackets(); i++) {
                Object inPacket = in.readObject();
                while (inPacket == null) {
                    inPacket = in.readObject();
                }
                Packet packet = (Packet) inPacket;
                out.writeObject(response);
                out.flush();
                packets.add(packet);
                System.out.println("Client : received from server " + packet + ", client-response " + response);
            }
        }

        //Sending from client to server
        List<Packet> packetList = getPackets();
        System.out.println("Client : packets " + packetList);
        out.writeObject(new Request(0, packetList.size()));
        out.flush();

        inObject = in.readObject();
        while (inObject == null) {
            inObject = in.readObject();
        }

        if (inObject instanceof Response) {
            Response response = (Response) inObject;
            if (response.getStatus().name().equals(Status.OK.name())) {
                for (int i = 0; i < packetList.size(); i++) {
                    Packet packet = packetList.get(i);
                    out.writeObject(packet);
                    out.flush();
                    Object packetObject = in.readObject();
                    while (packetObject == null) {
                        packetObject = in.readObject();
                    }
                    Response packetResponse = null;
                    if (packetObject instanceof Response) {
                        packetResponse = (Response) packetObject;
                        System.out.println("Client : - " + i + " sent packet " + packet + ", server-response " + packetResponse);
                    }
                    if (!packetResponse.getStatus().name().equals(Status.OK.name())) {
                        System.out.println("Client : stopped sending packets , server-response " + packetResponse);
                        break;
                    }
                }
            }
        }

        in.close();
        out.close();
        socket.close();
    }


    public static List<Packet> getPackets() {
        List<Packet> packetList = new ArrayList<>();
        packetList.add(new Packet(1, 0, "Client Testing"));
        packetList.add(new Packet(1, 0, "Client Hello"));
        packetList.add(new Packet(1, 0, "Client Packets"));
        return packetList;

    }
}
