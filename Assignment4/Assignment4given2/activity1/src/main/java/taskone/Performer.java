/**
  File: Performer.java
  Author: Student in Fall 2020B
  Description: Performer class in package taskone.
*/

package taskone;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

/**
 * Class: Performer 
 * Description: Threaded Performer for server tasks.
 */
class Performer {

    private StringList state;
    private Socket conn;

    public Performer(Socket sock, StringList strings) {
        this.conn = sock;
        this.state = strings;
    }


    public JSONObject add(String str) {
        JSONObject json = new JSONObject();
        json.put("datatype", 1);
        json.put("type", "add");
        state.add(str);
        json.put("data", state.toString());
        return json;
    }

    public JSONObject  remove(int index) {
        JSONObject json = new JSONObject();
        //Error check to make sure an integer was in fact passed to the server
        if(index > state.size()) {
            json = error("Error 2 - out of bounds, re-enter a number within bounds");
        }else {
            json.put("datatype", 2);
            json.put("type", "remove");
            String removed = state.remove(index);
            json.put("data", "Removed the String \"" + removed + "\".");
        }
        return json;
    }
    
    public JSONObject display() {
        JSONObject json = new JSONObject();
        json.put("datatype", 3);
        json.put("type", "display");
        json.put("data", state.toString());
        return json;
    }
    
    public JSONObject count() {
        JSONObject json = new JSONObject();
        json.put("datatype", 4);
        json.put("type", "count");
        json.put("data", String.valueOf(state.size()));
        return json;
    }
    
    public JSONObject reverse(int index) {
        JSONObject json = new JSONObject();
      //Error check to make sure an integer was in fact passed to the server
        if(index > state.size()) {
            json = error("Error 2 - out of bounds, re-enter a number within bounds");
        }else {
            json.put("datatype", 5);
            json.put("type", "reverse");
            state.reverse(index);
            json.put("data", state.toString());
        }
            return json;
    }
    public static JSONObject quit(Socket sock) {
        JSONObject json = new JSONObject();
        json.put("datatype", 0);
        json.put("type", "quit");
        json.put("data", "quitting");
            return json;
    }
    
    public static JSONObject error(String err) {
        JSONObject json = new JSONObject();
        json.put("error", err);
        return json;
    }

    public void doPerform() {
        boolean quit = false;
        OutputStream out = null;
        InputStream in = null;
        try {
            out = conn.getOutputStream();
            in = conn.getInputStream();
            System.out.println("Server connected to client:");
            while (!quit) {
                byte[] messageBytes = NetworkUtils.receive(in);
                //this is the data storage spot for the data coming in
                JSONObject message = JsonUtils.fromByteArray(messageBytes);
              //this is the data storage spot for the data we want to send out
                JSONObject returnMessage = new JSONObject();
   
                int choice = message.getInt("selected");
                    switch (choice) {
                        case (1):
                            String inStr = (String) message.get("data");
                            returnMessage = add(inStr);
                            break;
                        case (2):
                            //Checks if data is what we need otherwise 
                            try {
                                String str = (String) message.get("data");
                                int incomingInt =  Integer.parseInt(str);
                                returnMessage = remove(incomingInt);
                            }catch (Exception e) {
                                returnMessage = error("Error 1 - not and INTERGER");
                            }
                            break;
                        case (3):
                            returnMessage = display();
                            break;
                        case (4):
                            returnMessage = count();
                            break;
                        case (5):
                            try {
                                String dataStr = (String) message.get("data");
                                int inInt =  Integer.parseInt(dataStr);
                                returnMessage = reverse(inInt);
                            }catch (Exception e) {
                                returnMessage = error("Error 1 - not and INTERGER");
                            }
                            break;
                        case (0):
                            returnMessage = quit(conn);  
                            quit = true;
                            break;
                        default:
                            returnMessage = error("Invalid selection: " + choice 
                                    + " is not an option");
                            break;
                    }
                // we are converting the JSON object we have to a byte[]
                byte[] output = JsonUtils.toByteArray(returnMessage);
                NetworkUtils.send(out, output);
            }
            // close the resource
            System.out.println("close the resources of client ");
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
