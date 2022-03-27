/**
  File: Client.java
  Author: Student in Fall 2020B
  Description: Client class in package taskone.
*/

package taskone;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.json.JSONObject;

/**
 * Class: Client
 * Description: Client tasks.
 */
public class Client {
    private static BufferedReader stdin;

    /**
     * Function JSONObject add().
     * @author Melhase
     */
    public static JSONObject add() {
        String strToSend = null;
        JSONObject request = new JSONObject();
        request.put("selected", 1);
        try {
            System.out.print("Please input the string: ");
            strToSend = stdin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        request.put("data", strToSend);
        return request;
    }

    /**
     * Function JSONObject remove().
     * 
     * Description: Creates new json and reads next line to send data to server
     * @author shaunvulaj
     */
    public static JSONObject remove() {
        String strToSend = null;
        int inNum;
        JSONObject request = new JSONObject();
        request.put("selected", 2);
        try {
            System.out.print("Please input the positioon you would like to remove: ");
            strToSend = stdin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        request.put("data", strToSend);
        return request;
    }

    /**
     * Function JSONObject display().
     * Description: Displays string(in our case the StringList)
     * @author shaun vulaj
     */
    public static JSONObject display() {
        JSONObject request = new JSONObject();
        request.put("selected", 3);
        request.put("data", "");
        return request;
    }

    /**
     * Function JSONObject count().
     * Description: Tells us the size of the list
     * @author shaun vulaj
     */
    public static JSONObject count() {
        JSONObject request = new JSONObject();
        request.put("selected", 4);
        request.put("data", "");
        return request;
    }

    /**
     * Function JSONObject reverse().
     * Description: Reverses a target string
     * @author shaun vulaj
     */
    public static JSONObject reverse() {
        String strToSend = null;
        int inNum;
        JSONObject request = new JSONObject();
        request.put("selected", 5);
        try {
            System.out.print("Please input the position you would like to reverse: ");
            strToSend = stdin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        request.put("data", strToSend);
        return request;
    }

    /**
     * Function JSONObject quit().
     * Description: Exits clients connection to the server
     * @author shaun vulaj
     */
    public static JSONObject quit() {
        JSONObject request = new JSONObject();
        request.put("selected", 0);
        request.put("data", "");
        return request;
    }

    /**
     * Function main().
     */
    public static void main(String[] args) throws IOException {
        String host = null;
        int port= -1;
        Socket sock;
        stdin = new BufferedReader(new InputStreamReader(System.in));
        try {
            try {
                port = Integer.parseInt(args[1]);
                host = args[0];
                System.out.println("port= " + port);
            } catch (NumberFormatException nfe) {
                System.out.println("[Port] must be an integer");
                System.exit(2);
            }
            
            sock = new Socket(host, port);
            OutputStream out = sock.getOutputStream();
            InputStream in = sock.getInputStream();
            Scanner input = new Scanner(System.in);
            String choice;
            //General menu for the services
            do {
                System.out.println();
                // TODO: you will need to change the menu based on the tasks for this assignment, see Readme!
                System.out.println("Client Menu");
                System.out.println("Please select a valid option (1-5). 0 to diconnect the client");
                System.out.println("1. add <string> - adds a string to the list and display it");
                System.out.println("2. remove - remove a specified elemnt");
                System.out.println("3. display - display the list");
                System.out.println("4. count - returns the elements in the list");
                System.out.println("5. reverse - reverse a target string");
                System.out.println("0. quit");
                System.out.println();
                choice = input.nextLine(); // what if not int.. should error handle this
                JSONObject request = null;
                switch (choice) {
                    case ("1"):
                        request = add();
                        break;
                    case ("2"):
                        request = remove();
                        break;
                    case ("3"):
                        request = display();
                        break;
                    case ("4"):
                        request = count();
                        break;
                    case ("5"):
                        request = reverse();
                        break;
                    case ("0"):
                        request = quit();
                        break;
                    default:
                        System.out.println("Please select a valid option (0-6).");
                        break;
                }
                if (request != null) {
                    System.out.println(request);
                    NetworkUtils.send(out, JsonUtils.toByteArray(request));
                    byte[] responseBytes = NetworkUtils.receive(in);
                    JSONObject response = JsonUtils.fromByteArray(responseBytes);

                    if (response.has("error")) {
                        System.out.println(response.getString("error"));
                    } else {
                        System.out.println();
                        System.out.println("The response from the server: ");
                        System.out.println("datatype: " + response.getString("type"));
                        System.out.println("data: " + response.getString("data"));
                        System.out.println();
                        String typeStr = (String) response.getString("type");
                        if (typeStr.equals("quit")) {
                            sock.close();
                            out.close();
                            in.close();
                            System.exit(0);
                        }
                    }
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}