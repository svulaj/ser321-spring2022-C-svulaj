import java.net.*;
import java.io.*;
import org.json.*;

/**
 * A class to demonstrate a simple client-server connection using sockets.
 * Ser321 Foundations of Distributed Software Systems see
 * http://pooh.poly.asu.edu/Ser321
 * 
 * @author Tim Lindquist Tim.Lindquist@asu.edu Software Engineering, CIDSE,
 *         IAFSE, ASU Poly
 * @version August 2020
 * 
 * @modified-by David Clements <dacleme1@asu.edu> September 2020
 */

// This code is just very rought and you can keep it that way. It is only
// supposed to give you a rough skeleton with very rough error handling to make
// sure
// the server will not crash. Should in theory be done a bit cleaner but the
// main pupose here is to practice creating a protocol so I want to keep it as
// simple
// as possible.

public class SockServer {
  public static void main(final String args[]) {
    Socket sock;
    final int port = Integer.parseInt(args[0]); // no error handling on input arguments, you can assume they are correct
                                                // here

    try { // basic try catch just to catch exceptions
      // open socket
      final ServerSocket serv = new ServerSocket(port); // create server socket on port 8888
      while (true) {
        try { // this is not very pretty BUT it will keep the server running even if the
              // client sends a bad request which you might not handle in the server. The
              // server will print the
              // stacktrace and then wait for a new connection. The client will just stay in
              // wait for a response which it might never get

          System.out.println("Server ready for connections");

          sock = serv.accept(); // blocking wait

          // setup the object reading channel
          final ObjectInputStream in = new ObjectInputStream(sock.getInputStream());

          // get output channel
          final OutputStream out = sock.getOutputStream();
          // create an object output writer (Java only)
          final ObjectOutputStream os = new ObjectOutputStream(out);

          // Read request
          final String s = (String) in.readObject();

          // QUESTION: What could you do to make sure the server does not crash if the
          // client does not send a valid JSON?
          final JSONObject request = new JSONObject(s);

          System.out.println("Received the JSON " + request);

          final JSONObject response = new JSONObject();

          // this is the part where you would add your own service. Replace the two
          // services here with your services and try to handle the part where the client
          // might send a wrong request
          if (request.getString("type").equals("perm")) {
            // Boolean to test if we are dealing with digits or random characters
            boolean test = true;
            if (request.has("data")){
              if (request.get("data").getClass().getSimpleName().equals("String")) { // ok case
                // try{

                // }catch(NumberFormatException e){
                  
                // }

                // Data checkpoint to see if the String in question is a digit or not
                for(int i = 0; i < request.getString("data").length();i++){
                  if(!Character.isDigit(request.getString("data").charAt(i))){
                    test = false;
                  }
                }
                // 2nd Data checkpoint to see if the String in question is a digit or not
                for(int i = 0; i < request.getString("data2").length();i++){
                  if(!Character.isDigit(request.getString("data2").charAt(i))){
                    test = false;
                  }
                }
                if(request.getString("data") == "" || request.getString("data2") == ""){
                  test = false;
                }
                // If we are in fact deadling with digits then we can do work
                if(test == true){
                int input = Integer.parseInt((String) request.get("data"));
                int input2 = Integer.parseInt((String) request.get("data2"));
                
                //========================================================================
                //Permutation formula (  (data1)! / (data1 - data2)! ).
                // 1st factorial section -  we do the factorial of the first number
                int temp = input - 1;
                int result = input;
                for (int i = 0; i < input; i++) {
                  if ( temp == 1 ) {
                    break;
                  }
                  result = result * temp;
                  System.out.println(result);
                  
                  temp--; 
                }
                System.out.println("-----");
                // --------------------------------------------------
                // Second factorial section
                temp = 0;
                int max = input - input2;
                int result2 = input - input2;
                temp = result2 - 1;
                for (int j = 0; j < max; j++) {
                  if ( temp == 0 ) {
                    break;
                  }
                  System.out.println(result2);
                  result2 = result2 * temp;
                  System.out.println(result2);
                  
                  temp--;
                }
                int result3 = 0;
                try{
                  result3 = result/result2;
                }catch(ArithmeticException e){
                  System.out.println("cant divide by zero");
                }
                
                response.put("type", "perm");
                response.put("data", result3);
                response.put("data10", 1);
                // ========================================================================
                //simply prints the data we are going to be sending back to the client
                System.out.println("Sending the JSON " + response);
              } 
              else{
                response.put("type", "error");
                response.put("message", "Not a Integer");
              }
            }else{
              response.put("type", "error");
              response.put("message", "Not a Integer");
            }
          }
            test = true;
          } 
          ////////////////////////////////////////////////////////////////////////////////////////////
          else if (request.getString("type").equals("pal")) {
            boolean test = true;
            response.put("type", "pal");
            StringBuilder input1 = new StringBuilder();
            input1.append(request.getString("data"));
            
            System.out.println("INPUT chack " + input1);
            System.out.println("----------------------------");

            //creating blank string to be able to use reverse operation for pali. check
            StringBuilder blank = new StringBuilder();
            StringBuilder palindrome = blank.append(request.getString("data").toString());
            palindrome.reverse();
            
            System.out.println("PALINDRPME chack: " + palindrome);

              for(int i = 0; i < palindrome.length();i++){
                if(palindrome.charAt(i) != input1.charAt(i)){
                  // System.out.println("pali: " + palindrome.charAt(i));
                  // System.out.println("input1: " + input1.charAt(i));
                  response.put("data", "Is NOT a palindrome");
                  test = false;
                }
              }

              if(test == true){
                response.put("data", "Is a palindrome");
              }
            //simply prints the data we are going to be sending back to the client
            System.out.println("Sending the JSON " + response);
            
          }else if (request.getString("type").equals("exit")) {
            response.put("type", "exit");
            response.put("data", "Good bye!");
            // write the whole message
            os.writeObject(response.toString());
            // make sure it wrote and doesn't get cached in a buffer
            os.flush();

            // closing off the connection
            // sock.close();
            // in.close();
            // out.close();
            // serv.close();
            // break;
          } else { // very basic error handling here and one type or error message if request type
                   // is not known
            response.put("type", "error");
            response.put("message", "Request type not known");
          }

          // write the whole message
          os.writeObject(response.toString());
          // make sure it wrote and doesn't get cached in a buffer
          os.flush();

        } catch (final Exception e) { // this is in case something in your protocol goes wrong then the server
                                      // connection should still stay open
          e.printStackTrace();
        }
      }

    } catch (final Exception e) {
      e.printStackTrace();
    }
  }
}