
/**
 * Write a description of class Jugadores here.
 * 
 * @author Ronald Gutierrez Quispe
 * @version 0.4
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class Jugadores extends Thread
{
    public boolean espera=true;
    public ArrayList<Jugador> lista = new ArrayList<Jugador>();
    public void run()
    {
        System.out.println("Iniciando espera de jugadores");
        try
        {
             MulticastSocket socket = new MulticastSocket(4050);
             InetAddress address = InetAddress.getByName("ff01::1");
             socket.joinGroup(address);
             DatagramPacket packet;
             byte[] buf = new byte[256];
             while(espera)
             {
                 System.out.println("Esperando jugador");
                 packet = new DatagramPacket(buf, buf.length);
                 socket.receive(packet);
                 String received = new String(packet.getData(), 0, packet.getLength());
                 System.out.println("Jugador: "+received);
             }
             socket.leaveGroup(address);
             socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
