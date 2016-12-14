import java.io.*;
import java.net.*;
import java.util.*;

public class MulticastServerThread extends QuoteServerThread {

    private long FIVE_SECONDS = 5000;

    public MulticastServerThread() throws IOException {
        super("MulticastServerThread");
    }

    public void run() {
        System.out.println("iniciando....");
        while (moreQuotes) {
            System.out.println("mas notas....");
            try {
                byte[] buf = new byte[256];

                    // construct quote
                String dString = null;
                if (in == null)
                    dString = new Date().toString();
                else
                    dString = getNextQuote();
                buf = dString.getBytes();

            // send it
                InetAddress group = InetAddress.getByName("ff01::1");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4443);
                System.out.println("Enviando: "+dString);
                socket.send(packet);

            // sleep for a while
        try {
            sleep((long)(Math.random() * FIVE_SECONDS));
        } catch (InterruptedException e) { }
            } catch (IOException e) {
                e.printStackTrace();
        moreQuotes = false;
            }
        }
    socket.close();
    }
}