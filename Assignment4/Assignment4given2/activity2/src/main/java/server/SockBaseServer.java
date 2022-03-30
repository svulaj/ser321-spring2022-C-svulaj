package server;

import java.net.*;
import java.net.Authenticator.RequestorType;
import java.awt.Checkbox;
import java.awt.Frame;
import java.io.*;
import java.util.*;

import javax.imageio.plugins.tiff.FaxTIFFTagSet;

//import org.jcp.xml.dsig.internal.dom.DOMSubTreeData;
//import org.json.*;
import org.w3c.dom.DOMStringList;

import java.lang.*;

import buffers.RequestProtos.Request;
import buffers.RequestProtos.Logs;
import buffers.RequestProtos.Message;
import buffers.ResponseProtos.Response;
import client.Player;
import buffers.ResponseProtos.Entry;

class SockBaseServer {
    static String logFilename = "logs.txt";
    static String leaderFilename = "leaderboard.txt";
    ServerSocket serv = null;
    InputStream in = null;
    OutputStream out = null;
    Socket clientSocket = null;
    int port = 9099; // default port
    Game game;
    
    File inputFile = new File("leaderboard.txt");
    //File outputFile = new File("output.txt");
    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;
    
    List<String> dataList = new ArrayList<String>();
    Entry leader;
    

    int totalWins = 0;
    int logins = 1;
    boolean sameLeader = false;
    boolean gameAlreadyStarted = false;
    Response.Builder res = Response.newBuilder()
            .setResponseType(Response.ResponseType.LEADER);

    public SockBaseServer(Socket sock, Game game){
        this.clientSocket = sock;
        this.game = game;
        //this.game.newGame();
        try {
            in = clientSocket.getInputStream();
            out = clientSocket.getOutputStream();
        } catch (Exception e){
            System.out.println("Error in constructor: " + e);
        }
    }

    // Handles the communication right now it just accepts one input and then is done you should make sure the server stays open
    // can handle multiple requests and does not crash when the server crashes
    // you can use this server as based or start a new one if you prefer. 
    public void start() throws IOException {

        //leaderRead();
        //leaderWrite("chungusChan ","70 ","5");
        //leaderWrite("crackerjack",7,7);
        String name = "";


        System.out.println("Ready...");
        try {
            // read the proto object and put into new objct
            Request op = Request.parseDelimitedFrom(in);
            String result = null;

            

            // if the operation is NAME (so the beginning then say there is a commention and greet the client)
            if (op.getOperationType() == Request.OperationType.NAME) {
                // get name from proto object
            name = op.getName();
            
//            leader = Entry.newBuilder()
//                    .setName(name)
//                    .setWins(totalWins)
//                    .setLogins(logins)
//                    .build();
          if(sameLeader == true) {
              System.out.println("is true");
              for(int k = 0; k < dataList.size();k++) {
                  String strings[] = dataList.get(k).split(" ");      
                      if(name.equals(strings[0])) {
                          System.out.println("found the same person in begining of game");
                          logins = Integer.parseInt(strings[2]) + 1;
                          leaderWrite(name,Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
                          sameLeader = true;
                          //dataList.add(name + " " + String.valueOf(totalWins) + " " + String.valueOf(logins));
                      }
             }
          }
          
          else if(sameLeader == false) {
              System.out.println("is false");
                totalWins = 0;
                logins = 1;
                leader = Entry.newBuilder()
                        .setName(name)
                        .setWins(totalWins)
                        .setLogins(logins)
                        .build();
                leaderWrite(name,totalWins, logins);
                //dataList.add(name + " " + String.valueOf(totalWins) + " " + String.valueOf(logins));
            }
            
          //leaderRead();
            

            // writing a connect message to the log with name and CONNENCT
            writeToLog(name, Message.CONNECT);
                System.out.println("Got a connection and a name: " + name);
                Response response = Response.newBuilder()
                        .setResponseType(Response.ResponseType.GREETING)
                        .setMessage("Hello " + name + " and welcome. Welcome to a simple game of battleship. ")
                        .build();
                response.writeDelimitedTo(out);
            }
            
            
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //GAME LOOP BEGINS
            while(true) {
                
                //#15 work
                if(op.getOperationType() == Request.OperationType.QUIT) {
                    clientSocket.close();
                }else {
                    op = Request.parseDelimitedFrom(in);
                }
                
                
                // Example how to start a new game and how to build a response with the image which you could then send to the server
                // LINE 67-108 are just an example for Protobuf and how to work with the differnt types. They DO NOT
                // belong into this code. 
                //===========================================================================================
                if (op.getOperationType() == Request.OperationType.NEW) {
                
//                    game = new Game();
//                    game.newGame(); // starting a new game
                
                
                // adding the String of the game to 
                Response response2 = Response.newBuilder()
                    .setResponseType(Response.ResponseType.TASK)
                    .setImage(game.getImage())
                    .setTask("Game start: Select a row and column. or type 'exit' to disconnect ")
                    .build();
                
                // On the client side you would receive a Response object which is the same as the one in line 70, so now you could read the fields
                System.out.println("Task: " + response2.getResponseType());
                System.out.println("Image: \n" + response2.getImage());
                System.out.println("Task: \n" + response2.getTask());
                
                
                response2.writeDelimitedTo(out);  // <<<-----
                }
                else if (op.getOperationType() == Request.OperationType.ROWCOL) {
                    
                    
                    

                    //handles out of bounds
                    if(op.getRow() > 6 || op.getRow() < 0 || op.getColumn() > 6 || op.getColumn() < 0){
                        Response response3 = Response.newBuilder()
                                .setResponseType(Response.ResponseType.ERROR).setMessage("You must enter a value within bounds" + 
                        "E.G. not below zero and less than 7, or letters a - g").setTask("0").build();
                        response3.writeDelimitedTo(out);
                    }else {
                        System.out.println("Row coming in is: " + op.getRow());
                        System.out.println("Column coming in is: " + op.getColumn());
                        
                        //gather old score for win or lose checking
                        int gameScore = game.getIdx();
                        game.replaceOneCharacter(op.getRow(), op.getColumn());
                        
                        if(game.getIdx() >= 2) {
                            Response response2 = Response.newBuilder()
                                    .setResponseType(Response.ResponseType.WON)
                                    .setImage(game.getImage())
                                    .setTask("YOU HAVE WON!!!" + "\nYOUR score is: " + String.valueOf(game.getIdx()) + " CONGRATZ!!!")
                                    .build();
                            game.setIdx(0);
                            
                            //update same players win count
                            for(int r = 0; r < dataList.size();r++) {
                                System.out.println("updating wins");
                                String strings[] = dataList.get(r).split(" ");
                                    if(strings[0].equals(name)) {
                                        totalWins = Integer.parseInt(strings[1]) + 1;
                                        sameLeader = true;
                                        break;
                                    }
                            }
                            
                            //find player 
                            int index = 0;
                            for(index = 0; index < dataList.size(); index++) {
                                System.out.println("looking for player to replace");
                                String strings[] = dataList.get(index).split(" ");
                                System.out.println("REPLACING: " + strings[0] + " " + strings[1] + " " + strings[2]);
                                if(strings[0].equals(name)) {
                                    System.out.println("replacing this leader in leader list: below");
                                    System.out.println(res.getLeader(index));
                                    break;
                                }
                            }
                            
                            
                            res.setLeader(index, leader);
                            System.out.println();
                            leaderWrite(name, totalWins, logins);
                            leaderRead();
                          game = new Game();
                          game.newGame(); // starting a new game
                            response2.writeDelimitedTo(out);
                        }
                        
                        //miss condition
                        if(game.getIdx() == gameScore) {
                            Response response2 = Response.newBuilder()
                                    .setResponseType(Response.ResponseType.TASK)
                                    .setImage(game.getImage())
                                    .setTask("you made a move and MISSED" + "\nnow the score is: " + String.valueOf(game.getIdx()) + " BUT! What is your next move?")
                                    .build();
                            System.out.println("Score(no hit)----> " + game.getIdx());
                            response2.writeDelimitedTo(out);
//                            game.replaceOneCharacter(port, port)
                        }//win condition
                        else if(game.getIdx() > gameScore) {
                            Response response2 = Response.newBuilder()
                                    .setResponseType(Response.ResponseType.TASK)
                                    .setImage(game.getImage())
                                    .setTask("you made a move and HIT" + "\nnow the score is: " + String.valueOf(game.getIdx()) + " BUT! What is your next move?")
                                    .build();
                            System.out.println("Score(hit)----> " + game.getIdx());
                            response2.writeDelimitedTo(out);
//                            game.replaceOneCharacter(port, port)
                        }
                        System.out.println("========================================================");
                        
                    }
                    
                    
                    
                    
                }
              //===========================================================================================
                else if (op.getOperationType() == Request.OperationType.LEADER) {
                    System.out.println("you are trying to manage/access the leaderboard");
                    
                    Response response3 = res.build();
                    response3.writeDelimitedTo(out);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (out != null)  out.close();
            if (in != null)   in.close();
            if (clientSocket != null) clientSocket.close();
            dataList = null;
        }
    }


    /**
     * Writing a new entry to our log
     * @param name - Name of the person logging in
     * @param message - type Message from Protobuf which is the message to be written in the log (e.g. Connect) 
     * @return String of the new hidden image
     */
    public static void writeToLog(String name, Message message){
        try {
            // read old log file 
            Logs.Builder logs = readLogFile();

            // get current time and data
            Date date = java.util.Calendar.getInstance().getTime();
            System.out.println(date);

            // we are writing a new log entry to our log
            // add a new log entry to the log list of the Protobuf object
            logs.addLog(date.toString() + ": " +  name + " - " + message);

            // open log file
            FileOutputStream output = new FileOutputStream(logFilename);
            Logs logsObj = logs.build();

            // This is only to show how you can iterate through a Logs object which is a protobuf object
            // which has a repeated field "log"

            for (String log: logsObj.getLogList()){

                System.out.println(log);
            }

            // write to log file
            logsObj.writeTo(output);
        }catch(Exception e){
            System.out.println("Issue while trying to save");
        }
    }

    /**
     * Reading the current log file
     * @return Logs.Builder a builder of a logs entry from protobuf
     */
    public static Logs.Builder readLogFile() throws Exception{
        Logs.Builder logs = Logs.newBuilder();

        try {
            // just read the file and put what is in it into the logs object
            return logs.mergeFrom(new FileInputStream(logFilename));
        } catch (FileNotFoundException e) {
            System.out.println(logFilename + ": File not found.  Creating a new file.");
            return logs;
        }
    }
    
    public void leaderWrite(String name, int wins, int logins) {
               
        
        
                try {
                   bufferedReader = new BufferedReader(new FileReader(inputFile));
                   bufferedWriter = new BufferedWriter(new FileWriter(inputFile));
                   
                   for(int s = 0; s < dataList.size();s++) {
                       String strings[] = dataList.get(s).split(" ");
                       if(name.equals(strings[0])) {
                           System.out.println("duplicate player is found <----");
                           String updatedEntryString = strings[0] + " " + wins + " " + logins;
                           System.out.println(updatedEntryString + " has been added in for ");
                           System.out.println("---->" + dataList.get(s));
                           dataList.set(s, updatedEntryString);
                           break;
                       }
                   }
                   dataList.add(name + " " + String.valueOf(wins) + " " + String.valueOf(logins));
                   for(int i = 0; i < dataList.size();i++) {
                       bufferedWriter.write(dataList.get(i));
                       bufferedWriter.newLine();
                   }
                   
                } catch (Exception e) {
                   e.printStackTrace();
                }finally {
                    try {
                        bufferedReader.close();
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();                 
                      }
                }
    }
    
    public void leaderRead() {
        
        try {
           bufferedReader = new BufferedReader(new FileReader(inputFile));
           String line;
           while((line = bufferedReader.readLine()) != null) {
               
               //read in old data
               dataList.add(line);
               //add data to leader list
               String strings[] = line.split(" ");
               try {
                totalWins = Integer.parseInt(strings[1]);
                logins = Integer.parseInt(strings[2]);
                
                Entry leader = Entry.newBuilder().setName(strings[0]).setWins(totalWins).setLogins(logins).build();
                res.addLeader(leader);
                System.out.println(line);
                } catch (Exception e) {
                    System.out.println("error when parsing for leader list");
                  }
               
           }
        } catch (Exception e) {
           e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();                 
              }
        }
}


    public static void main (String args[]) throws Exception {
        Game game = new Game();
        //game.newGame();

        if (args.length != 2) {
            System.out.println("Expected arguments: <port(int)> <delay(int)>");
            System.exit(1);
        }
        int port = 9099; // default port
        int sleepDelay = 10000; // default delay
        Socket clientSocket = null;
        ServerSocket serv = null;

        try {
            port = Integer.parseInt(args[0]);
            sleepDelay = Integer.parseInt(args[1]);
        } catch (NumberFormatException nfe) {
            System.out.println("[Port|sleepDelay] must be an integer");
            System.exit(2);
        }
        try {
            serv = new ServerSocket(port);
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
        while (true) {
        clientSocket = serv.accept();
        serverThread server = new serverThread(clientSocket, game);
        //SockBaseServer server = new SockBaseServer(clientSocket, game);
        server.start();
        }
    }
    

    
    
    
}

