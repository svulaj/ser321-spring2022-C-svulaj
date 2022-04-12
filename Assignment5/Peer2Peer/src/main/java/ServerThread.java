import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


import org.json.*;


/**
 * SERVER
 * This is the ServerThread class that has a socket where we accept clients contacting us.
 * We save the clients ports connecting to the server into a List in this class. 
 * When we wand to send a message we send it to all the listening ports
 */

public class ServerThread extends Thread{
	private ServerSocket serverSocket;
	private Set<Socket> listeningSockets = new HashSet<Socket>();

	private LinkedList<Integer> newPorts = new LinkedList<>();
	private Peer peer;

	private String allPorts;

	
	public ServerThread(String portNum) throws IOException {
		serverSocket = new ServerSocket(Integer.valueOf(portNum));

	}
	
	/**
	 * Starting the thread, we are waiting for clients wanting to talk to us, then save the socket in a list
	 */
	public void run() {
		try {
			while (true) {
				Socket sock = serverSocket.accept();
				System.out.println("howdy dowdy ----> accepted");
				listeningSockets.add(sock);
				System.out.println("Listening triggered ---->" + listeningSockets.size());



				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				JSONObject json = new JSONObject(bufferedReader.readLine());

				if(json.getString("type").equals("ONE")){
					allPorts = json.getString("data") + " ";
					System.out.println(allPorts);
					newPorts.add(json.getInt("data"));
					System.out.println(json.toString());
					Socket socket;
						try{
							socket = new Socket("localhost", json.getInt("data"));
							new ClientThread(socket).start();
							PrintWriter sout = new PrintWriter(socket.getOutputStream(), true);

							JSONObject json2 = new JSONObject();
							json2.put("type","TWO");
							sout.println(json2.toString());
						}catch(Exception e){
							System.out.println("ONE!!!");
						}
				}

				else if(json.getString("type").equals("TWO")){

					listToString(newPorts);
					System.out.println("woooooowww");

				}
				else if(json.getString("type").equals("THREE")){

				}
				else if(json.getString("type").equals("FOUR")){

				}
				else{
					System.out.println("Incorrect JSON type");
					System.exit(2);
				}


			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sending the message to the OutputStream for each socket that we saved
	 */
	void sendMessage(String message) {
		try {
			System.out.println( "========" +  listeningSockets.size() + message);
			for (Socket s : listeningSockets) {
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				System.out.println("flaka flame");
				out.println(message);
		     }
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public LinkedList<Integer> getNewPorts() {
		return newPorts;
	}

	public void setNewPorts(LinkedList<Integer> newPorts) {
		this.newPorts = newPorts;
	}

	public boolean listToString(LinkedList<Integer> list) throws IOException {
		String ans = "";
		for (Integer s : list) {
			String temp  = list.get(s).toString();
			ans += " " + temp;
		}
		System.out.println("The list as a string: " + ans);
		return false;
	}

}
