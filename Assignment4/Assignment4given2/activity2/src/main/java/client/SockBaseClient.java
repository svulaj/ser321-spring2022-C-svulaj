package client;

import java.net.*;
import java.awt.desktop.QuitEvent;
import java.io.*;

import org.json.*;

import buffers.RequestProtos.Request;
import buffers.ResponseProtos.Response;
import buffers.ResponseProtos.Response.ResponseType;
import buffers.ResponseProtos.Entry;

import java.util.*;
import java.util.stream.Collectors;

class SockBaseClient {

    public static void main (String args[]) throws Exception {
        boolean quit = false;
        Socket serverSock = null;
        OutputStream out = null;
        InputStream in = null;
        int i1=0, i2=0;
        int port = 9099; // default port
//=======================================================================================
        //SECTION FOR ARGUMENT PASSING
        // Make sure two arguments are given
        if (args.length != 2) {
            System.out.println("Expected arguments: <host(String)> <port(int)>");
            System.exit(1);
        }
        String host = args[0];
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException nfe) {
            System.out.println("[Port] must be integer");
            System.exit(2);
        }
//=======================================================================================
        //SECTION ---> ASK FOR USERNAME
        System.out.println("Please provide your name for the server.");
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String strToSend = stdin.readLine();

        // Build the first request object just including the name
        Request op = Request.newBuilder().setOperationType(Request.OperationType.NAME).setName(strToSend).build();
        Response response;
        try {
            // connect to the server
            serverSock = new Socket(host, port);

            // write to the server
            out = serverSock.getOutputStream();
            in = serverSock.getInputStream();

            op.writeDelimitedTo(out);

            // read from the server
            response = Response.parseDelimitedFrom(in);

            // print the server response. 
            System.out.println(response.getMessage());
            
            
            
            while(quit == false) {
//==================================================================================================================
            // MENU SECTION
            System.out.println("* \nWhat would you like to do? \n 1 - to see the leader board \n 2 - to enter a game \n 3 - quit the game");

            strToSend = stdin.readLine();
            switch (strToSend) {
            case "1": {
                System.out.println("you have chosen the leaderboard option");
                //--------------------------------------------------------------------------------
                //send out
                op = Request.newBuilder().setOperationType(Request.OperationType.LEADER).build();
                op.writeDelimitedTo(out);
               //--------------------------------------------------------------------------------
                //read in
                response = Response.parseDelimitedFrom(in);
                    for (Entry lead: response.getLeaderList()){
                        System.out.println(lead.getName() + ": " + lead.getWins());
                    }
                break;
            }
            case "2": {
                // BEGINING OF GAME
                op = Request.newBuilder().setOperationType(Request.OperationType.NEW).build();
                op.writeDelimitedTo(out);
                response = Response.parseDelimitedFrom(in);
                
                //QUESTION PHASE
                //write out
                //asks the question
                System.out.println(response.getImage() + "\n" + response.getTask());
                op.writeDelimitedTo(out);
                //read in
                response = Response.parseDelimitedFrom(in);
                strToSend = stdin.readLine();
                //if the want to disconnect
                if(strToSend.equals("exit")) {
                    op = Request.newBuilder().setOperationType(Request.OperationType.QUIT).build();
                    op.writeDelimitedTo(out);
                    quit = true;
                    break;
                }
                else if(!Character.isDigit(strToSend.charAt(1))) {
                    System.out.println("Error-1 ---> You did not input a valid digit!");
                }else if(strToSend.length() != 2) {
                    System.out.println("Error 1 ---> You must end an input of (E.g. b0, a letter followed by a numeric value, no spaces)");
                }else {
                    strToSend = strToSend.toUpperCase();
                    char row = strToSend.charAt(0);
                    int col = Character.getNumericValue(strToSend.charAt(1));
                    op = Request.newBuilder().setOperationType(Request.OperationType.ROWCOL).setRow(row - 'A').setColumn(col).build();
                    op.writeDelimitedTo(out); 
                }
                response = Response.parseDelimitedFrom(in);
//--------------------------------------------------------------------------
                //ERROR HANDLING SECTION
                // NEW - LET TOMMY KNOW
                if(response.getResponseType() == ResponseType.ERROR) {
                    if(response.getTask().equals("0")) {
                        System.out.println(response.getMessage());
                    }else {
                        System.out.println("fart buckets");
                    }
//--------------------------------------------------------------------------
                // if passes error handling print score and then update board
                }else {
                    System.out.println(response.getTask());
                    System.out.println(response.getImage());
                }
                
                
                
                break;
            }
            case "3": {
                op = Request.newBuilder().setOperationType(Request.OperationType.QUIT).build();
                op.writeDelimitedTo(out);
                quit = true;
                break;
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + strToSend);
            }
//            response = Response.parseDelimitedFrom(in);
//            System.out.println(response.getLeader(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null)   in.close();
            if (out != null)  out.close();
            if (serverSock != null) serverSock.close();
        }
    }
        
}


