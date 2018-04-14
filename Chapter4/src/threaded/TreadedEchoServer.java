package threaded;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * This program implements a multithreaded server that listens to port 8180 nad echoes back
 * all client input
 */
public class TreadedEchoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8180)){
            int i = 1;

            while(true) {
                Socket incoming = serverSocket.accept();
                System.out.println("Spawning " + i);
                Runnable r = new TreadEchoHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

class TreadEchoHandler implements Runnable {
    private Socket incoming;

    /**
     * Constructs a handler.
     * @param incomingSocket
     */
    public TreadEchoHandler(Socket incomingSocket) {
        this.incoming = incomingSocket;
    }


    @Override
    public void run() {
        try (InputStream inStream = incoming.getInputStream();
             OutputStream outStream = incoming.getOutputStream()) {
            Scanner in = new Scanner(inStream, "UTF-8");
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(outStream, "UTF-8"),
                    true
            );

            out.println("Hello Enter BYE to exit.");

            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                out.println("Echo: " + line);
                if (line.trim().equals("BYE")) done=true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}