package main.java;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.*;
import org.json.*;
import org.json.*;


public class Leader {
    
    private static int port;
    private static String host;
    private static ArrayList<Socket> nodeSockets =  new ArrayList<Socket>();
    private static Socket socket;
    private static Socket node1Socket;
    private static Socket node2Socket;
    private static Socket clientSocket;
    private static Socket clientSocket2;
    private static PrintWriter sout;
    private static int creditOnTab;
    private static String r1;
    private static int tracker;
    
public static void main(String[] args){
    
   
    OutputStream out = null;
    InputStream in = null;
    ServerSocket server = null;
    BufferedReader bufferedReader;
    BufferedReader bufferedReaderNode1;
    BufferedReader bufferedReaderNode2;
    JSONObject json;
    String string;
    int connections = 0;
    String clientID;
    int dollarAmount;
    
    boolean client = false;
    boolean node1 = false;
    boolean node2 = false;
    
    

     //Establishing communication & creating socket connection
        try {
                host = args[0];
                port =  Integer.parseInt(args[1]);
                server = new ServerSocket(port);
                System.out.println("You are in");
                
                
                
                
                node1Socket = server.accept();
                nodeSockets.add(node1Socket);
                connections++;
                bufferedReaderNode1 = new BufferedReader(new InputStreamReader(node1Socket.getInputStream())); 
                System.out.println("node1Socket connected");
                
                
                node2Socket = server.accept();
                nodeSockets.add(node2Socket);
                connections++;
                System.out.println("node2Socket connected");
                bufferedReaderNode2 = new BufferedReader(new InputStreamReader(node2Socket.getInputStream())); 
                
                
                
                clientSocket = server.accept();
                connections++;
                bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                
                
              //ask for client id
                json = new JSONObject();
                json.put("type", "id");
                json.put("data", "what is your clientID?");
                sout = new PrintWriter(clientSocket.getOutputStream(), true);
                sout.println(json.toString());
                
                
                
                
                
                
                while(true){
                    
                    
                        //will always keep reading
                        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        json = new JSONObject(bufferedReader.readLine());
                    
                   
                    //-----------------------------------------------------------------------------
                    // ERROR HANDLING FOR CLIENTS TO CONNECT FIRST
                    
                    
                    //for beginning cycle: asks client for id so to send to nodes to save
                    if(json.getString("type").equals("IDresponse")) {
                        //print ID retrieved
                        string = json.getString("data");
                        System.out.println("Received an id from client: " + string);
                        
                        //ask for client id
                        json = new JSONObject();
                        json.put("type", "clientID");
                        json.put("data", string);
                        //sends id to the nodes
                        sendToNodes(json);
                        
                        
                        json = new JSONObject(bufferedReaderNode1.readLine());
                        string = json.getString("data");
                        System.out.println("Received from node: " + string);
                        
                        json = new JSONObject(bufferedReaderNode2.readLine());
                        string = json.getString("data");
                        System.out.println("Received from node: " + string);
                        
                        
                        
                        //ask client for choice
                        json = new JSONObject();
                        json.put("type", "choice");
                        json.put("data", "Choose what you want to do here are your options: 1) credit or 2) pay-back. please spell as diplayed");
                        sout = new PrintWriter(clientSocket.getOutputStream(), true);
                        sout.println(json.toString());
                        
                    }
                    
                    //will now prompt client for next steps
                    else if(json.getString("type").equals("idAccepted")) {
                        receiveFromNodes();
                        
                        
                        //ask for client id
                        json = new JSONObject();
                        json.put("type", "clientID");
                        json.put("data", "options: credit  or   pay-back. please spell as diplayed");
                        sout = new PrintWriter(clientSocket.getOutputStream(), true);
                        sout.println(json.toString());
                        
                    }
                    
                    
                    else if(json.getString("type").equals("response")) {
                        
                        string = json.getString("data");
                        System.out.println("the choice the client made: " + string);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//credit section
                        if(string.equals("credit")) {
                            
                            //ASKS HOW MUCH CREDIT THE CLIENT IS ASKING FOR
                          //sending choice to nodes
                            json = new JSONObject();
                            json.put("type", "creditchoice");
                            json.put("data", "How much credit would you like?");
                            sout = new PrintWriter(clientSocket.getOutputStream(), true);
                            sout.println(json.toString());
                            
                            //WAITS FOR RESPONSE(THE DOLLAR AMOUNT)
                            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                            json = new JSONObject(bufferedReader.readLine());
                            
                            
                            string = json.getString("data");
                            System.out.println("credit amount recieved: " + string);
                            dollarAmount = Integer.parseInt(string);
                            
                            //SEND DOLLAR AMOUNT TO THE NODES TO GET CONFRIMATION
                            json = new JSONObject();
                            json.put("type", "wantcredit");
                            json.put("data", string);
                            
                            
                            String node1Confirmation;
                            String node2Confirmation;
                            int tempDollar2;
                            //SENDS DOLLAR AMOUNT TO THE NODES SO THE CAN SEE IF THEY HAVE THE MONEY
                            sendToNodes(json);
                            //COLLECTING & DISPLAYING CONF. MSG'S
                            json = new JSONObject(bufferedReaderNode1.readLine());
                            node1Confirmation = json.getString("data");
                            json = new JSONObject(bufferedReaderNode2.readLine());
                            node2Confirmation = json.getString("data");
                            
                            System.out.println("Answers from the nodes: " + node1Confirmation + " and " + node2Confirmation);
                            
                            //both nodes accepted condition
                            if(node1Confirmation.equals("yes") && node2Confirmation.equals("yes")) {
                              //will send conf. to credit
                                json = new JSONObject();
                                json.put("type", "yes");
                                json.put("data", "yes");
                                sendToNodes(json);
                                
                                json = new JSONObject(bufferedReaderNode1.readLine());
                                r1 = json.getString("data");
                                json = new JSONObject(bufferedReaderNode2.readLine());
                                String r2 = json.getString("data");
                                System.out.println("Answers from the nodes after confirmation stuff: " + r1 + " and " + r2);
                                
                                //creditOnTab = (Integer.parseInt(r1) + Integer.parseInt(r2));
                                int one = Integer.parseInt(r1);
                                int two = Integer.parseInt(r2);
                                int three = one + two;
                                String senderString = Integer.toString(three);
                                
                                creditOnTab = one;
                                //WHEN ACCEPTED RE-LOOPS FOR OPTIONS
                                json = new JSONObject();
                                json.put("type", "choice");
                                json.put("data", "Credit=ACCEPTED.<<<<-----\nyou now have " + one + " in credit" +"\nyour options are: 1) credit or 2) pay-back. please spell as diplayed");
                                sout = new PrintWriter(clientSocket.getOutputStream(), true);
                                sout.println(json.toString());
                                
                                
                            }else if(node1Confirmation.equals("no") && node2Confirmation.equals("no") || 
                                    node1Confirmation.equals("yes") && node2Confirmation.equals("no") ||
                                    node1Confirmation.equals("no") && node2Confirmation.equals("yes")){
                                System.out.println("Leader is replying to node data flags");
                                System.out.println("Sending ---> no");
                                json = new JSONObject();
                                json.put("type", "no");
                                json.put("data", "no");
                                sendToNodes(json);
                                
                                
                                json = new JSONObject(bufferedReaderNode1.readLine());
                                node1Confirmation = json.getString("data");
                                json = new JSONObject(bufferedReaderNode2.readLine());
                                node2Confirmation = json.getString("data");
                                
                                
                                
                                
                                //THIS IS WHERE NOTHING HAPPENS IF THE CONSENSES IS "MONEY NOT AVAILABLE"
                                if(node1Confirmation.equals("cancelled") && node2Confirmation.equals("cancelled")) {
                                    System.out.println("Node1 said: " + node1Confirmation + " and node2 said: " + node2Confirmation);
                                  //ask client for choice
                                    json = new JSONObject();
                                    json.put("type", "choice");
                                    json.put("data", "Credit=DECLINED.<<<<-----\nyour options are: 1) credit or 2) pay-back. please spell as diplayed");
                                    sout = new PrintWriter(clientSocket.getOutputStream(), true);
                                    sout.println(json.toString());
                                }
                                
                             
                            }
                            
                            
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//pay-back section              
                        }else if(string.equals("pay-back")) {
                              //ASKS HOW MUCH CREDIT THE CLIENT IS ASKING FOR
                              //sending choice to nodes
                              json = new JSONObject();
                              json.put("type", "paychoice");
                              json.put("data", "How much would you like to pay-back?");
                              sout = new PrintWriter(clientSocket.getOutputStream(), true);
                              sout.println(json.toString());
                              
                              //WAITS FOR RESPONSE(THE DOLLAR AMOUNT)
                              bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                              json = new JSONObject(bufferedReader.readLine());
                              
                              //displays pay-back amount
                              string = json.getString("data");
                              System.out.println("pay-back amount recieved from client: " + string);
                              tracker = Integer.parseInt(string);
                              //sends value to nodes
                              json = new JSONObject();
                              json.put("type", "wantpay");
                              json.put("data", string);
                              sendToNodes(json);
                              
                              //---------------------------------------------------------
                              
                              json = new JSONObject(bufferedReaderNode1.readLine());
                              String n1 = json.getString("data");
                              json = new JSONObject(bufferedReaderNode2.readLine());
                              String n2 = json.getString("data");
                              
                              int n3 = Integer.parseInt(n1);
                              int n4 = Integer.parseInt(n2);
                              int n5 = n3 + n4;
                              
                              System.out.println("data coming back is: " + n1 + " and "+ n2);
                              System.out.println("check 1 <<<<<<<<<----------");
                              //ERROR CASE TO COVER IF THERE IS NO CREDIT TO BE PAID ON BY THE CLIENT.
                              if(json.getString("type").equals("error")) {
                                //WHEN ACCEPTED RE-LOOPS FOR OPTIONS
                                  json = new JSONObject();
                                  json.put("type", "choice");
                                  json.put("data", "NO CREDIT TO PAY.<<<<-----\nyou now have " + creditOnTab + " in credit" +"\nyour options are: 1) credit or 2) pay-back. please spell as diplayed");
                                  sout = new PrintWriter(clientSocket.getOutputStream(), true);
                                  sout.println(json.toString());
                                  //CASE WHERE THERE IS CREDIT TO BE PAID ON
                              }else{
                                 
                                  int temp = Integer.parseInt(n1) + Integer.parseInt(n2);
                                  System.out.println("temp --->>>>>>" + temp);
                          
                                  creditOnTab = creditOnTab - tracker;
                                  System.out.println("credit tab ==== " + creditOnTab);
                                  json = new JSONObject();
                                  json.put("type", "choice");
                                  json.put("data", "Payment SUCCESS.<<<<-----\nyou now have " + creditOnTab  + " in credit" +"\nyour options are: 1) credit or 2) pay-back. please spell as diplayed");
                                  sout = new PrintWriter(clientSocket.getOutputStream(), true);
                                  sout.println(json.toString());
                                  
                              }
                              
                              
                              
                        }
                       
                        
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    }
                    
                    
                    
                    
                    System.out.println("LOOPING.... what the");
                    




    
                    
                    
                }

        
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//main

/**
 * Description: simply sends a Json to all known nodes.
 * @param message
 */
public static void sendToNodes(JSONObject message) {
    try {
        for(Socket s : nodeSockets) {
            sout = new PrintWriter(s.getOutputStream(), true);
            sout.println(message.toString());
        }
        
    }catch (Exception e) {
        System.out.println("error brother");
    }
    
}

public static void receiveFromNodes() {
    String string = " ";
    String string2 = " ";
    JSONObject json = new JSONObject();
    JSONObject json2 = new JSONObject();
    
    
    try {
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(node1Socket.getInputStream()));
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(node2Socket.getInputStream()));
        
        
            
            json = new JSONObject(bufferedReader1.readLine());
            json2 = new JSONObject(bufferedReader2.readLine());
            
            string = json.getString("data");
            System.out.println("-----------");
            string2 = json.getString("data");
        
    }catch (Exception e) {
        System.out.println("error brother");
    }
    
    
    
}

}







//else if(json.getString("type").equals("response")) {
//string = json.getString("data");
//
//if(string.equals("credit")) {
//  System.out.println("Client has chosen: " + string);
//  json = new JSONObject();
//  json.put("type", "creditchoice");
//  json.put("data", "How much credit would you like to take?");
//  sout = new PrintWriter(clientSocket.getOutputStream(), true);
//  sout.println(json.toString());
//  
//}else if(string.equals("pay-back")) {
//  System.out.println("Client has chosen: " + string);
//  json = new JSONObject();
//  json.put("type", "paybackchoice");
//  json.put("data", "How much would you like to payback?");
//  sout = new PrintWriter(clientSocket.getOutputStream(), true);
//  sout.println(json.toString());
//}
//}
