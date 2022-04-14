package main.java;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.*;
import org.json.*;



public class Leader {
    
    private static int port;
    private static String host;
    
public static void main(String[] args){
    
    Socket socket;
    Socket node1Socket;
    Socket node2Socket;
    Socket clientSocket;
    Socket clientSocket2;
    OutputStream out = null;
    InputStream in = null;
    ServerSocket server = null;
    BufferedReader bufferedReader;
    PrintWriter sout;
    JSONObject json;
    String string;
    int connections = 0;
    String clientID;
    ArrayList<Socket> nodeSockets =  new ArrayList<Socket>();
    

     //Establishing communication & creating socket connection
        try {
            
                
            
                host = args[0];
                port =  Integer.parseInt(args[1]);
                server = new ServerSocket(port);
                System.out.println("You are in");
                
                
                
                
                node1Socket = server.accept();
                connections++;
                System.out.println("node1Socket connected");
                node2Socket = server.accept();
                connections++;
                System.out.println("node2Socket connected");
                clientSocket = server.accept();
                connections++;
                
              //ask for client id
                json = new JSONObject();
                json.put("type", "confirm");
                json.put("data", "are you a client?");
                sout = new PrintWriter(clientSocket.getOutputStream(), true);
                sout.println(json.toString());
                
                
                
                while(true){
                    //will always keep reading
                    bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    json = new JSONObject(bufferedReader.readLine());
                    //-----------------------------------------------------------------------------
                    // ERROR HANDLING FOR CLIENTS TO CONNECT FIRST
                    if(json.getString("type").equals("confimation")) {
                        if(json.getString("data").equals("yes")) {
                            System.out.println("client has connected");
                            json = new JSONObject();
                            json.put("type", "id");
                            json.put("data", "What is your client ID?");
                            sout = new PrintWriter(clientSocket.getOutputStream(), true);
                            sout.println(json.toString());
                            
                        }else if(json.getString("data").equals("no")) {
                          //ask for client id
                            json = new JSONObject();
                            json.put("type", "error");
                            json.put("data", "you are not a client, please connect client first");
                            sout = new PrintWriter(clientSocket.getOutputStream(), true);
                            sout.println(json.toString());
                        }
                        
                        
                    }
                  //-----------------------------------------------------------------------------
                    
                    if(json.getString("type").equals("IDresponse")) {
                        //displays the client id that the leader has received.
                        string = json.getString("data");
                        
                        
                        //assigning clientID to a variable for safe keeping
                        clientID = string;
                        System.out.println("Recieved from client: " + clientID);
                       //==================================================================
                        json = new JSONObject();
                        json.put("type", "choice");
                        json.put("data", "Please enter whether or not this is a pay-back or credit: (E.g. credit/pay-back)");
                        sout = new PrintWriter(clientSocket.getOutputStream(), true);
                        sout.println(json.toString());
                        
                      }
                    else if(json.getString("type").equals("response")) {
                        string = json.getString("data");
                        
                        if(string.equals("credit")) {
                            System.out.println("Client has chosen: " + string);
                            json = new JSONObject();
                            json.put("type", "creditchoice");
                            json.put("data", "How much credit would you like to take?");
                            sout = new PrintWriter(clientSocket.getOutputStream(), true);
                            sout.println(json.toString());
                            
                        }else if(string.equals("pay-back")) {
                            System.out.println("Client has chosen: " + string);
                            json = new JSONObject();
                            json.put("type", "paybackchoice");
                            json.put("data", "How much would you like to payback?");
                            sout = new PrintWriter(clientSocket.getOutputStream(), true);
                            sout.println(json.toString());
                        }
                    }
                    
                    else if(json.getString("type").equals("credit")) {
                        string = json.getString("data");
                        System.out.println(string);
                        System.out.println("done");
                        
                        json = new JSONObject();
                        json.put("type", "wantcredit");
                        json.put("data", string);
                        sout = new PrintWriter(node1Socket.getOutputStream(), true);
                        sout.println(json.toString());
                        sout = new PrintWriter(node2Socket.getOutputStream(), true);
                        sout.println(json.toString());
                        
                        
                    }
                    else if(json.getString("type").equals("pay-back")) {
                        string = json.getString("data");
                        System.out.println(string);
                        System.out.println("done");
                    }
                    
                    
                    
                }

        
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
