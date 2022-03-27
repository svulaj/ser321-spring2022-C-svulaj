package client;

import java.net.*;
import java.awt.desktop.QuitEvent;
import java.io.*;

import org.json.*;

import buffers.RequestProtos.Request;
import buffers.ResponseProtos.Response;
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
                op = Request.newBuilder().setOperationType(Request.OperationType.NEW).setName(strToSend).build();
                break;
            }
            case "3": {
                op = Request.newBuilder().setOperationType(Request.OperationType.QUIT).setName(strToSend).build();
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


