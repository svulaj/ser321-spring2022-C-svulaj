package taskone;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadPoolServer implements Runnable{
    private static int bufLen = 1024;
    private Socket socket;
    private StringList strlist;
    //have in-case i want for later
    private int id;
    private int sleepDelay;
    
    //Constructor
    public ThreadPoolServer(Socket sock, StringList list, int sleep) {
        this.socket = sock;
        this.strlist = list;
        
        this.sleepDelay = sleep;
      }
    
    public void run() {
        Performer performer = new Performer(socket, strlist);
        performer.doPerform();
        try {
            Thread.sleep(sleepDelay);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        try {
            //closes the connection
            socket.close();
            } catch (IOException e) {
                System.out.println("Can't get I/O for the connection.");
            }
    }
    
    public static void main(String args[]) throws Exception{
        Socket sock;
        int id = 0;
        StringList stringsList = new StringList();
        
        int sleepDelay = 10; // default value
        int numWorkers = 3; // default value
        int poolSize = numWorkers - 1;
        
        try {
          
          int portNo = Integer.parseInt(args[0]);
          
          ServerSocket serv = new ServerSocket(portNo);
          System.out.println("ThreadedPoolserver waiting for connects on port " + portNo);
            
          Executor pool = Executors.newFixedThreadPool(poolSize);
          while (true) {
              sock = serv.accept();
              System.out.println("ThreadedPoolserver connected to client: ");
              pool.execute(new ThreadPoolServer(sock, stringsList,sleepDelay));
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
    
    
    
    
}
