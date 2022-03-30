package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import buffers.RequestProtos.Logs;
import buffers.RequestProtos.Message;
import buffers.RequestProtos.Request;
import buffers.ResponseProtos.Entry;
import buffers.ResponseProtos.Response;

public class serverThread extends Thread{
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

    Response.Builder res = Response.newBuilder()
            .setResponseType(Response.ResponseType.LEADER);

    public serverThread(Socket sock, Game game){
        this.clientSocket = sock;
        this.game = game;
        game.newGame();
        try {
            in = clientSocket.getInputStream();
            out = clientSocket.getOutputStream();
        } catch (Exception e){
            System.out.println("Error in constructor: " + e);
        }
    }
    
    
    public void run(){

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
                    
//                game = new Game();
//                game.newGame(); // starting a new game
                
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
                    
                    
                    
//                   op.getOperationType().getNumber(); <<<<<------COULD BE USED AS FLAG
                    //NEW --> LET TOMMY KNOW handles out of bounds
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
//                        System.out.println("GAMEScore-----> " + gameScore);
//                        System.out.println("Score-----> " + game.getIdx());
                        game.replaceOneCharacter(op.getRow(), op.getColumn());
//                        System.out.println("Score2----> " + game.getIdx());
                        
                        if(game.getIdx() >= 12) {
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
                                //System.out.println("looking for player to replace");
                                String strings[] = dataList.get(index).split(" ");
                                //System.out.println("REPLACING: " + strings[0] + " " + strings[1] + " " + strings[2]);
                                if(strings[0].equals(name)) {
                                    //System.out.println("replacing this leader in leader list: below");
                                    //System.out.println(res.getLeader(index));
                                    break;
                                }
                            }
                            
                            
                            //res.setLeader(index, leader);
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
                    // Creating Entry and Leader response
//                    Response.Builder res = Response.newBuilder()
//                        .setResponseType(Response.ResponseType.LEADER);
                    
//                    // building an Entry for the leaderboard
//                    Entry leader = Entry.newBuilder()
//                        .setName("Shaun")
//                        .setWins(0)
//                        .setLogins(0)
//                        .build();
//        
//                    // building another Entry for the leaderboard
//                    Entry leader2 = Entry.newBuilder()
//                        .setName("Tommy")
//                        .setWins(1)
//                        .setLogins(1)
//                        .build();
//        
//                    // adding entries to the leaderboard
//                    res.addLeader(leader);
//                    res.addLeader(leader2);
        
                    // building the response 
                    Response response3 = res.build();
                    
                    // iterating through the current leaderboard and showing the entries
//                    for (Entry lead: response3.getLeaderList()){
//                        System.out.println(lead.getName() + ": " + lead.getWins());
//                    }
                    response3.writeDelimitedTo(out);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (out != null)  out.close();
                if (in != null)   in.close();
                if (clientSocket != null) clientSocket.close();
                dataList = null;
            } catch (Exception e) {
                System.out.println("wow");
            }
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
    
}
