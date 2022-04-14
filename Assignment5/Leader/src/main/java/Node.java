package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import org.json.*;

public class Node {
    private static int money;
    private static int port;
    private static String host;

    public static void main(String[] args){
        
        Socket socket;
        OutputStream out = null;
        InputStream in = null;
        ServerSocket server = null;
        Scanner scanner = new Scanner(System.in);
        ObjectOutputStream os;
        BufferedReader bufferedReader;
        JSONObject json;
        PrintWriter sout;
        String string;
        
        //bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        

        //This section deals with whether or not a money value has been passed by the user
        if(args.length == 2) {
            money = 2000;
            System.out.println("No money passed so money = " + money);
        }else {
          //accepts money passed in
            money = Integer.parseInt(args[2]);
        }
        
        
        //This section established the connection with the clients and the nodes
        try {
            host = args[0];
            port = Integer.parseInt(args[1]);
            socket = new Socket(host,port);
            System.out.println("Hooked up!");
            
            
            
            System.out.println("Money on system is: " + money);
            
            while(true) {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                json = new JSONObject(bufferedReader.readLine());
                
                if(json.getString("type").equals("wantcredit")) {
                    string = json.getString("data");
                    int valueWanted = Integer.parseInt(string);
                    
                    System.out.println("Value received for credit request: " + valueWanted);
                    
                }
            }
            
            
            
            
            
            

            
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        Node.money = money;
    }
}
