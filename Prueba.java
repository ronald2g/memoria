
/**
 * Write a description of class Prueba here.
 * 
 * @author Ronald 
 * @version (a version number or a date)
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class Prueba
{
    public static void main(String[] args)
    {
        try
        {
            DatagramSocket socket = null;
            BufferedReader in = null; 
            byte[] buf = new byte[256];
            String dString="hola";
            buf = dString.getBytes();
            socket = new DatagramSocket();
            InetAddress group = InetAddress.getByName("ff01::1");
            DatagramPacket packet = new DatagramPacket(buf, buf.length, group,4050);
            System.out.println("Enviando: "+dString);
            socket.send(packet);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void probar()
    {
        Mensaje a=new Mensaje();
        a.asignar("nombre","Ronald Gutierrez");
        a.asignar("nivel","5");
        System.out.println(a);
        System.out.println(a.entregar());
    }
    
    public static void probar2()
    {
        (new Jugadores()).start();
    }
}
