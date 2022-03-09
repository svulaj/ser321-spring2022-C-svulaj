package Assignment3Starter;

import java.awt.Dimension;

import org.json.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

/**
 * The ClientGui class is a GUI frontend that displays an image grid, an input text box,
 * a button, and a text area for status. 
 * 
 * Methods of Interest
 * ----------------------
 * show(boolean modal) - Shows the GUI frame with the current state
 *     -> modal means that it opens the GUI and suspends background processes. Processing 
 *        still happens in the GUI. If it is desired to continue processing in the 
 *        background, set modal to false.
 * newGame(int dimension) - Start a new game with a grid of dimension x dimension size
 * insertImage(String filename, int row, int col) - Inserts an image into the grid
 * appendOutput(String message) - Appends text to the output panel
 * submitClicked() - Button handler for the submit button in the output panel
 * 
 * Notes
 * -----------
 * > Does not show when created. show() must be called to show he GUI.
 * 
 */
//==============================================================================================
	//Input/Output streams and Json init's:



//==============================================================================================
public class ClientGui implements Assignment3Starter.OutputPanel.EventHandlers {
	JDialog frame;
	PicturePanel picturePanel;
	OutputPanel outputPanel;
	boolean gameStarted = false;
	String currentMessage;
	Socket sock;
	OutputStream out;
	ObjectOutputStream os;
	BufferedReader bufferedReader;
	//==============================================================================================



	//==============================================================================================
	// TODO: SHOULD NOT BE HARDCODED
	String host = "localhost";
	int port = 9000;

	/**
	 * Construct dialog
	 * @throws IOException 
	 */
	public ClientGui(String host, int port) throws IOException {
		this.host = host; 
		this.port = port; 
	
		frame = new JDialog();
		frame.setLayout(new GridBagLayout());
		frame.setMinimumSize(new Dimension(500, 500));
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// setup the top picture frame
		picturePanel = new PicturePanel();
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.25;
		frame.add(picturePanel, c);

		// setup the input, button, and output area
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0.75;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		outputPanel = new OutputPanel();
		outputPanel.addEventHandlers(this);
		frame.add(outputPanel, c);

		picturePanel.newGame(1);
		insertImage("img/hi.png", 0, 0);

		open(); // opening server connection here
		currentMessage = "{'type': 'start'}"; // very initial start message for the connection
		try {
			os.writeObject(currentMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String string = this.bufferedReader.readLine();
		System.out.println("Got a connection to server");
		JSONObject json = new JSONObject(string);
		outputPanel.appendOutput(json.getString("value")); // putting the message in the outputpanel

		// reading out the image (abstracted here as just a string)
		//System.out.println("Pretend I got an image: " + json.getString("image"));

		/// would put image in picture panel
		close(); //closing the connection to server

		// Now Client interaction only happens when the submit button is used, see "submitClicked()" method
	}

	/**
	 * Shows the current state in the GUI
	 * @param makeModal - true to make a modal window, false disables modal behavior
	 */
	public void show(boolean makeModal) {
		frame.pack();
		frame.setModal(makeModal);
		frame.setVisible(true);
	}

	/**
	 * Creates a new game and set the size of the grid 
	 * @param dimension - the size of the grid will be dimension x dimension
	 * No changes should be needed here
	 */
	public void newGame(int dimension) {
		picturePanel.newGame(1);
		outputPanel.appendOutput("Started new game with a " + dimension + "x" + dimension + " board.");
	}

	/**
	 * Insert an image into the grid at position (col, row)
	 * 
	 * @param filename - filename relative to the root directory
	 * @param row - the row to insert into
	 * @param col - the column to insert into
	 * @return true if successful, false if an invalid coordinate was provided
	 * @throws IOException An error occured with your image file
	 */
	public boolean insertImage(String filename, int row, int col) throws IOException {
		System.out.println("Image insert");
		String error = "";
		try {
			// insert the image
			if (picturePanel.insertImage(filename, row, col)) {
				// put status in output
				//outputPanel.appendOutput("Inserting " + filename + " in position (" + row + ", " + col + ")"); // you can of course remove this
				return true;
			}
			error = "File(\"" + filename + "\") not found.";
		} catch(PicturePanel.InvalidCoordinateException e) {
			// put error in output
			error = e.toString();
		}
		outputPanel.appendOutput(error);
		return false;
	}

	/**
	 * Submit button handling
	 * 
	 * TODO: This is where your logic will go or where you will call appropriate methods you write. 
	 * Right now this method opens and closes the connection after every interaction, if you want to keep that or not is up to you. 
	 */
	@Override
	public void submitClicked() {
		try {
		open(); // opening a server connection again
		System.out.println("submit clicked ");
			String name = "";
		// Pulls the input box text
		String input = outputPanel.getInputText();
		final JSONObject request = new JSONObject();

			System.out.println("Received the JSON " + request);

			//gets name
			if(currentMessage.equals("{'type': 'start'}")){
				request.put("type", "name");
				request.put("name", input);
				System.out.println(name);
				currentMessage = "{'type': 'name'}";
				outputPanel.setInputText(""); //resets text box
			}
	//==================================================================================================================
			//gets number of questions
			else if(currentMessage.equals("{'type': 'name'}")){

				request.put("type", "n_question");//<-----------
				request.put("q_num", input);
				System.out.println( "sending----> " + request);
				outputPanel.setInputText(""); //resets text box
				currentMessage =  "{'type': 'begin'}";

			}
	//==================================================================================================================
			//Beginning of the game "Start sequence"
			else if(currentMessage.equals("{'type': 'begin'}")){
				request.put("type", "begin");
				request.put("payload", input);
				System.out.println( "sending----> " + request);
				outputPanel.setInputText(""); //resets text box

			}
	//==================================================================================================================
//			//loopback
//			else if(currentMessage.equals("{'type': 'quit'}")){
//				request.put("type", "quit");
//				request.put("payload", input);
//				System.out.println( "sending----> " + request);
//				outputPanel.setInputText(""); //resets text box
//			}


		// send request to server
			String string = "";
		try {
			  os.writeObject(request.toString()); // this will crash the server, since it is not a JSON and thus the server will not handle it.
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		// wait for an answer and handle accordingly
		try {
			System.out.println("Waiting on response");
			string = this.bufferedReader.readLine();
			System.out.println(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
			//Json object to capture server responses
			final JSONObject response = new JSONObject(string); // what if res is not a correct JSON?
//======================================================================================================================
//						>>>>>-------------- ERROR HANDLING SECTION --------------<<<<<

			// Error handling for number of questions being input
			if(response.getString("type").equals("error1")){
				//print the message
				outputPanel.appendOutput(response.getString("message"));
				//facilitates a loop till error is fixed by user
				currentMessage =  "{'type': 'name'}";
			}
			// Error handling for img junk(ATM)
			if(response.getString("type").equals("error2")){
				//print the message
				outputPanel.appendOutput(response.getString("message"));
				//facilitates a loop till error is fixed by user
				currentMessage =  "{'type': 'begin'}";
			}

//======================================================================================================================
//				>>>>>-------------- SUCCESS CASES FOR PROPER GAME SEQUENCE --------------<<<<<


			if (response.getString("type").equals("name")) {
					outputPanel.appendOutput("Hey " + response.getString("name") + " Lets play! How many times would you like to play??");
			}
			else if(response.getString("type").equals("n_question")){
					outputPanel.appendOutput(response.getString("q_num"));
			}
			else if(response.getString("type").equals("begin")){
					if(response.getString("new_game").equals("new")){
						currentMessage =  "{'type': 'start'}";
					}


					outputPanel.appendOutput(response.getString("extra_payload_info"));
					insertImage(response.getString("payload"), 0, 0);
			}







//=============================================================================================
		close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Key listener for the input text box
	 * 
	 * Change the behavior to whatever you need
	 */
	@Override
	public void inputUpdated(String input) {
		if (input.equals("surprise")) {
			outputPanel.appendOutput("You found me!");
		}
	}

	public void open() throws UnknownHostException, IOException {
		this.sock = new Socket(host, port); // connect to host and socket

		// get output channel
		this.out = sock.getOutputStream();
		// create an object output writer (Java only)
		this.os = new ObjectOutputStream(out);
		this.bufferedReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));

	}
	
	public void close() {
        try {
            if (out != null)  out.close();
            if (bufferedReader != null)   bufferedReader.close(); 
            if (sock != null) sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) throws IOException {
		// create the frame


		try {
			String host = "localhost";
			int port = 8888;


			ClientGui main = new ClientGui(host, port);
			main.show(true);


		} catch (Exception e) {e.printStackTrace();}


		//insertImage("/Users/shaunvulaj/Desktop/Spring 2022/SER 321/homework repo/ser321-spring2022-C-svulaj/Assignment3/Activity 3-2/Simple/img/lose.jpg", 0, 0);

	}
}
