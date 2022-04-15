package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.json.*;

public class Client {
    private static int port;
    private static String host;
     
    public static void main(String[] args){
        Socket socket;
        ServerSocket server = null;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        String string;
        JSONObject json;
        PrintWriter sout;
        
        try {
            host = args[0];
            port = Integer.parseInt(args[1]);
            socket = new Socket(host,port);
            System.out.println("Client has connected to the Leader!");
            
            

           
            //SERVER ASKS FOR ID HERE   <<<<<<---------------------
            while(true) {
            
            //------------------------------------------------------------------
            //reads in what the server sends over(right here is where leader asks for an ID#)
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            json = new JSONObject(bufferedReader.readLine());
            //------------------------------------------------------------------
/////////////////////////////////////////////////////////////////////////////////////////////////
            //ERROR SECTION
            if(json.getString("type").equals("error")) {
                string = json.getString("data");
                System.out.println(string);
            }
            
/////////////////////////////////////////////////////////////////////////////////////////////////
            // automated response confirming this client is in fact a client
            
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++           
//BELOW THIS LINE IS FUNCTIONALITY      
            
            
            //this on collects client ID
            else if(json.getString("type").equals("id")) {
                string = json.getString("data");
                System.out.println(string);
                
                //Will now take input for the ID so that we can send back to the server(Leader)
                //System.out.println("Input your ID: ");
                string = " ";
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                //read user input
                string = bufferedReader.readLine();
                //packaging up the input into a json
                json = new JSONObject();
                json.put("type", "IDresponse");
                json.put("data", string);
                //writes the json back out to the Leader
                sout = new PrintWriter(socket.getOutputStream(), true);
                sout.println(json.toString());
                
            }
            
            else if(json.getString("type").equals("choice")) {
                //display what Leader sent
                string = json.getString("data");
                System.out.println(string);
                //collect response and send back to the leader
                string = " ";
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                string = bufferedReader.readLine();
                //packaging up the input into a json
                json = new JSONObject();
                json.put("type", "response");
                json.put("data", string);
                //writes the json back out to the Leader
                sout = new PrintWriter(socket.getOutputStream(), true);
                sout.println(json.toString());
                
            }
            
            
            
            else if(json.getString("type").equals("creditchoice")) {
                //display what Leader sent
                string = json.getString("data");
                System.out.println(string);
                //collect response and send back to the leader
                string = " ";
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                string = bufferedReader.readLine();
                //packaging up the input into a json
                json = new JSONObject();
                json.put("type", "credit");
                json.put("data", string);
                //writes the json back out to the Leader
                sout = new PrintWriter(socket.getOutputStream(), true);
                sout.println(json.toString());
                
            }
//            
//            else if(json.getString("type").equals("paybackchoice")) {
//                //display what Leader sent
//                string = json.getString("data");
//                System.out.println(string);
//                //collect response and send back to the leader
//                string = " ";
//                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//                string = bufferedReader.readLine();
//                //packaging up the input into a json
//                json = new JSONObject();
//                json.put("type", "pay-back");
//                json.put("data", string);
//                //writes the json back out to the Leader
//                sout = new PrintWriter(socket.getOutputStream(), true);
//                sout.println(json.toString());
//                
//            }
            
           
            }
            
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
