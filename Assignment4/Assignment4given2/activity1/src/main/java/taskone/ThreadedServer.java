package taskone;


import java.net.*;
import java.io.*;
import java.util.*;

public class ThreadedServer  extends Thread{
    private static int bufLen = 1024;
    private Socket socket;
    private StringList strlist;
    //have in-case i want for later
    private int id;
    
    //Constructor
    public ThreadedServer(Socket sock, StringList list) {
        this.socket = sock;
        this.strlist = list;
      }
    
    
    
    public void run() {
        Performer performer = new Performer(socket, strlist);
        performer.doPerform();
        
        try {
            //closes the connection
            socket.close();
          } catch (IOException e) {
            System.out.println("Can't get I/O for the connection.");
          }
    }
    
    
    
    
    public static void main(String args[]) {
        Socket sock;
        
        int id = 0;
        StringList stringsList = new StringList();
        
        try {
          
          int portNo = Integer.parseInt(args[0]);
          if (portNo <= 1024)
            portNo = 8888;
          
          ServerSocket serv = new ServerSocket(portNo);
          while (true) {
            System.out.println("Threadedserver waiting for connects on port " + portNo);
            sock = serv.accept();
            System.out.println("Threadedserver connected to client: " + id);
            
            ThreadedServer server = new ThreadedServer(sock, stringsList);
            server.start();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
    
    
}
