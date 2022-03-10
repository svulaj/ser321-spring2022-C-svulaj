package Assignment3Starter;
import java.net.*;
import java.util.Base64;
import java.util.Set;
import java.util.Stack;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;
import java.io.*;
import org.json.*;


/**
 * A class to demonstrate a simple client-server connection using sockets.
 * Ser321 Foundations of Distributed Software Systems
 * see http://pooh.poly.asu.edu/Ser321
 * @author Tim Lindquist Tim.Lindquist@asu.edu
 *         Software Engineering, CIDSE, IAFSE, ASU Poly
 * @version August 2020
 * 
 * 
 * Description:
 * 		start is used to gather the information for the start condition of the system. (given)
 *
 * 		name is used to collect the data for the users name.
 * 						"name" is only used here to send the string appropriately.
 *
 * 		n_question is used to receive the number of questions/images the user would like to play with.
 * 						"q_num" is the variable used to perform this.(number of questions)
 *
 * 		begin is used for the main game.
 * 						"new" is used to focus when a new game is being requested(after they play once)
 * 						"payload" is used for image sending.
 * 						"extra_payload_info" is used for additional information needed to guide the game forwward.
 * 
 * request 										(starting the connection)
 * 	type: start
 * Response: 
 * OK
 *  type: hello
 *  image: <String> encoded image
 *  value: <String> asking for name of player
 * Error
 * 	type: error
 *  message: <String> Error message
 *
 * ============================================================================================
 *  request 										(collect name)
 * 	type: name
 * 	name: <String>
 *
 *  Response:
 *  OK
 *  type: name
 *  name: <String> send name
 *  Error
 *  type: error
 *  message: <String> Error message
 * ============================================================================================
 * request 									(how many number of questions?)
 * type: n_question
 * q_num: <String>
 *
 * Response
 * OK
 * type: n_question
 * q_num: <String>
 *
 * Error
 * type: error1								<----------- Error 1
 * message: <String> Error message
 *
 * String is:
 *     - "You didn't enter in anything" -- Empty string was received
 *     - "must enter a digit" -- User did not send a digit(Integer)
 *     - "Input must be 6 or less" -- User did not send a value that was less than or equal to 6(total amount of turns/pictures)
 *     - "You didnt enter anything in? must enter an integer" -- extra security of ensuring a integer value is passed
 *	   - "must enter value larger than 0" -- User did not enter anything/they input a 0.
 * ============================================================================================
 * request 									(Actual start of game)
 * type: begin
 * payload: <String>
 *
 * Response
 * OK
 * type: begin
 * payload: <String>
 * extra_payload_info: <String>
 * points: <String>
 * new_game; <String>
 *
 * Error
 * type: error2								<----------- Error 2
 * message: <String> error message
 *
 * String is:
 *     - "enter start when ready to start" -- Lets the user know they have to enter start to begin the game
 *     - "Cannot get any more \"more's\" for this round" -- Lets user know they have extinguished all of their "more" usages for the current turn
 *
 * **NOTE** --> You may see a stack track on the client terminal if you input a strange value,
 * 				when you are prompted to enter "start" but the program still works if you continue to input wrong values,
 * 				and will work properly as soon as you input "start"
 *
 *
 *
 * @modified-by David Clements <dacleme1@asu.edu> September 2020
 */
public class SockServer {

	static Stack<String> imageSource = new Stack<String>();
	public static int seconds = 0;			//used for timer
	public static int q_num = 0;			// Number of questions they wish to answer
	public static Timer game_timer;			//the timer

	public static void main (String args[]) {
		Socket sock;
		try {
			
			//opening the socket here, just hard coded since this is just a bas example
			ServerSocket serv = new ServerSocket(Integer.parseInt(args[0])); // TODO, should not be hardcoded
			System.out.println("Server ready for connetion");

			// placeholder for the person who wants to play a game
			String name = "";		//stores the name
			int points = 0;			//points for the game
			int clientID = 0;
			String num_q = "";		//string representation of the num. of questions input
			int turn = 0;			//Used to keep track of the turn they are on
			int internal_count = 0;  //Used for the more button -> pushes on to the next image within the photo_arr
//			int q_num = 0;			// Number of questions they wish to answer
			int current_photo_count = 0;
			int guesses = 0;
			boolean game_started = false;		//Is a flag used to signal game has started
			int count = 0;
			int more_count = 0;					//track number of mores inputed
			boolean next_game = false;
			boolean end_game = false;  			// governs win or lose conditions(a flag)
			boolean game_complete = false;

			boolean game_check;





			//String[] img_arr = new String[] {"img/car",};

			// read in one object, the message. we know a string was written only by knowing what the client sent. 
			// must cast the object from Object to desired type to be useful
			while(true) {
				sock = serv.accept(); // blocking wait
				// setup the object reading channel

				// could totally use other input outpur streams here
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
				OutputStream out = sock.getOutputStream();

				String s = (String) in.readObject();
				JSONObject json = new JSONObject(s); // the requests that is received

				JSONObject response = new JSONObject();

				if (json.getString("type").equals("start")){
					
					System.out.println("- Got a start");
					//signals for name collection
					response.put("type","hello" );
					response.put("value","Hello, please tell me your name." );
					sendPic("img/hi.png", response); // calling a method that will manipulate the image and will make it send ready
					
				}
	//==================================================================================================================
				else if (json.getString("type").equals("name")){		//<------------ NAME

					//System.out.println("- Got a start");
					//collects and stores the name (takes anything for the name0
					response.put("type", "name");
					response.put("name", json.getString("name"));
					name = json.getString("name");
					System.out.println("Users name is: " + name);
//					q_num = 6;
				}
	//==================================================================================================================
				else if (json.getString("type").equals("n_question")){		//<------------ NUM OF QUESTIONS
					//Declaring the Json we are talking about
					response.put("type", "n_question");
					//Storing the data from the request data, into the response data
					response.put("q_num", json.getString("q_num"));
					System.out.println( "receiving----> " + response);
					//----------------------------------------------------------------
					//Section handles the input of a number(q_num) to hold the int value of the number entered
					boolean test = true;

					num_q = response.getString("q_num");

					//checks if digit
					for(int i = 0; i < response.getString("q_num").length();i++){
						if(!Character.isDigit(response.getString("q_num").charAt(i))){
							test = false;
							System.out.println("fails digit check");
						}
					}


					//try to read what was passed in number-format catching
					try{
						//Empty string check
						if(response.get("q_num").equals("")){
							System.out.println("In try, failure - > not a string");
							response.put("type", "error1");
							response.put("message", "You didnt enter in anything");


						}
						//digit check pt.2
						else if(test == false){
							System.out.println("failure -->  digit check failed earlier");
							response.put("type", "error1");
							response.put("message", "must enter a digit");

						}
						//Larger than 6 check
						else if (Integer.parseInt((String) response.get("q_num")) > 6) {
							System.out.println("In try, failure - > greater than 6");
							response.put("type", "error1");
							response.put("message", "Input must be 6 or less");

						}
						//Success case
						else{
							q_num = Integer.parseInt((String) response.get("q_num"));
							response.put("q_num", "Alright " + name + ", " + num_q + " questions it is! Type \"Start\" when you are ready to play.");

						}}catch (NumberFormatException e){ //if something other than a number is input we signal to user
							response.put("type", "error1");
							response.put("message", "You didnt enter any thing in? must enter a integer");
						}
					//makes sure user doesnt input 0 turns
					if(q_num == 0){
						response.put("type", "error1");
						response.put("message", "must enter value larger than 0");
					}
						System.out.println("q_num = " + q_num);
				}
	//==================================================================================================================
				//signal to start the game
				else if (json.getString("type").equals("begin")){		//<------------Start of game {ADD TIMER HERE
//String[] cucumber_arr = new String[] {};
					String[] photo_arr = new String[] {"img/car/car1.png","img/car/car2.png","img/car/car3.png",
													"img/cat/cat1.png","img/cat/cat2.png","img/cat/cat3.png",
													"img/cucumber/cucumber1.png","img/cucumber/cucumber2.png","img/cucumber/cucumber3.png",
													"img/hat/hat1.png","img/hat/hat2.png","img/hat/hat3.png",
													"img/pug/pug1.png","img/pug/pug2.png","img/pug/pug3.png",
													"img/puppy/puppy1.png","img/puppy/puppy2.png","img/puppy/puppy3.png"};

					game_check = true;		//used as preventive measure on win/lose condition
					response.put("new_game", "old");

					//quick hops to locations in image array
					int start_of_cat = 3;
					int start_of_cucumber = 6;
					int start_of_hat = 9;
					int start_of_pug = 12;
					int start_of_puppy = 15;

					//recieve entering game signal
					response.put("type", "begin");
					//receives user input
					response.put("payload", json.getString("payload"));
					System.out.println( "receiving----> " + response);
					//stores user input for use
					String user_input = response.getString("payload");
					System.out.println(user_input);

					//whats left from one of my attempts to implement quit
//					if(user_input.equals("quit")){
//						response.put("extra_payload_info", "quit");
//						response.put("payload", "shutting down");
//
//
//					}

					//Error handling if start is not entered
					if(!user_input.equals("start") && turn == 0){

						System.out.println("In try, failure - > not a string");
						response.put("type", "error2");
						response.put("message", "enter start when ready to start");


					}
					//--------------------------------------------------
					//			  General first checks
					//--------------------------------------------------
					//if didn't receive a string == start check
					if(user_input.equals("start")){
						TimerTask task = new TimerTask() {


							@Override
							public void run() {
								seconds++;
								System.out.println("seconds passed: " + seconds);
								if(seconds > q_num*30){
									game_timer.cancel();
									return;
								}
							}
						};




						//new timer for each game
						game_timer = new Timer();
						//placement of where in the img_arr we are(acts a  i from for loop)
						internal_count = 0;
						//new game signals time refresh
						seconds = 0;
						//wishing them luck
						response.put("extra_payload_info", "good luck, timer has now stared!");
						//send opening image
						sendPic(photo_arr[internal_count],response);
						//signals game has started
						game_started = true;
						//times begins
						game_timer.scheduleAtFixedRate(task,1000,1000); //<<<<<<<------timer
					}
					//--------------------------------------------------
					//System.out.println("more (out): " + internal_count);
					//Pushes on to the next image
					if(user_input.equals("more")){
						if(more_count < 2){
							more_count++;
							internal_count++;
							//System.out.println("div of internal: " + (internal_count % 3));
							//System.out.println("more (in): " + internal_count);
							sendPic(photo_arr[internal_count],response);
							//if reach max more input we let the user know
						}else if(more_count == 2){
							response.put("type", "error2");
							response.put("message", "Cannot get any more \"more's\" for this round");
						}

					}
					//--------------------------------------------------
					//next functionality
					if(user_input.equals("next")){
						if(turn == 0){
							//prevents early image forward movement
							response.put("extra_payload_info","Easy now");
						}
						else if(turn == 1){
							response.put("extra_payload_info","You lose a point for using \"next\"");
							//if on car move to cat
							internal_count = start_of_cat;
							//send new image
							sendPic(photo_arr[internal_count],response);
							//increment the turn to keep pace with img_array location
							turn++;
							//user loses point for skipping
							points--;
						}
						else if(turn == 2){
							response.put("extra_payload_info","You lose a point for using \"next\"");
							//if on cat move to cucumber.
							internal_count = start_of_cucumber;
							sendPic(photo_arr[internal_count],response);
							turn++;
							points--;
						}
						else if(turn == 3){
							response.put("extra_payload_info","You lose a point for using \"next\"");
							internal_count = start_of_hat;
							sendPic(photo_arr[internal_count],response);
							turn++;
							points--;
						}
						else if(turn == 4){
							response.put("extra_payload_info","You lose a point for using \"next\"");
							internal_count = start_of_pug;
							sendPic(photo_arr[internal_count],response);
							turn++;
							points--;
						}
						else if(turn == 5){
							response.put("extra_payload_info","You lose a point for using \"next\"");
							internal_count = start_of_puppy;
							sendPic(photo_arr[internal_count],response);
							turn++;
							points--;
						}
						else if(turn == 6){
							response.put("extra_payload_info","End");

						}
					}
//					if(game_complete == true){
//						game_complete = false;
//						response.put("extra_payload_info", "new");
//					}

					//--------------------------------------------------

					//If start has been entered
					if(game_started == true && q_num != 0){
						System.out.println("(beginning)q_num = " + q_num);
						//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
						//beginning of game
						System.out.println("Turn = " + turn);
						if(turn == 1){
							if(!user_input.equals("car")){
								//if not correct answer we keep displaying this message no matter what
								response.put("extra_payload_info", "Try Again!, if you cannot figure it out enter:\"more\". You can enter:\"next\" but you will lose a point  --> 1");
							}
							else if(user_input.equals("car")){
								System.out.println("in car"); //for grading purposes
								//if over time, user loses
								if(seconds >= (30 * q_num)){
									System.out.println("in car timer check");
									//signals the game has ended
									end_game = true;
									//additional game state check
									game_check = false;
//									game_timer.cancel();
								}
								//gainz points
								points++;
								//congradulate
								response.put("extra_payload_info","good job, you got it! Total points = " + points);
								//increase turn
								turn++;
								System.out.println("(car success)Turn = " + turn);	//for grading
								//move to next image
								internal_count = start_of_cat;
								//send said image
								sendPic(photo_arr[internal_count],response);

								System.out.println("(car success)Internal_count = " + internal_count);
								//refresh more input count for next image guessing
								more_count = 0;
								System.out.println("(car success)More_count = " + more_count);
								//points++;
								//decrement number of question to track win/lose state
								q_num--;
								System.out.println("(q_num-- " + q_num);
								//additional state measures
								game_check = true;
							}
						}
						//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
						//turn 2
						else if(turn == 2){

							if(!user_input.equals("cat")){
								response.put("extra_payload_info", "Try Again!, if you cannot figure it out enter:\"more\" --> 2");
							}
							else if(user_input.equals("cat")){
								if(seconds >= (30 * q_num)){
									end_game = true;
									game_check = false;
									System.out.println("timer is up");
//									game_timer.cancel();
								}
								points++;
								response.put("extra_payload_info","good job, you got it! Total points = " + Integer.toString(points));
								//Increase turn to the next turn/round of guessing
								turn++;
								System.out.println("(cat success)Turn = " + turn);
								//You won so now the next round's image gets displayed
								internal_count = start_of_cucumber;
								sendPic(photo_arr[internal_count],response);

								System.out.println("(cat success)Internal_count = " + internal_count);
								more_count = 0;
								System.out.println("(cat success)More_count = " + more_count);
								q_num--;
								System.out.println("(q_num-- " + q_num);
								game_check = true;
							}
						}
						//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
						//turn 3
						else if(turn == 3){
							if(!user_input.equals("cucumber")){
								response.put("extra_payload_info", "Try Again!, if you cannot figure it out enter:\"more\" --> 3");
							}
							else if(user_input.equals("cucumber")){
								if(seconds >= (30 * q_num)){
									end_game = true;
									game_check = false;
//									game_timer.cancel();
								}

								points++;
								response.put("extra_payload_info","good job, you got it! Total points = " + Integer.toString(points));
								//Increase turn to the next turn/round of guessing
								turn++;
								System.out.println("(cucumber success)Turn = " + turn);
								//You won so now the next round's image gets displayed
								internal_count = start_of_hat;
								sendPic(photo_arr[internal_count],response);

								System.out.println("(cucumber success)Internal_count = " + internal_count);
								more_count = 0;
								System.out.println("(cucumber success)More_count = " + more_count);
								q_num--;
								System.out.println("(q_num-- " + q_num);
								game_check = true;
							}
						}
						else if(turn == 4){
							if(!user_input.equals("hat")){
								response.put("extra_payload_info", "Try Again!, if you cannot figure it out enter:\"more\" --> 3");
							}
							else if(user_input.equals("hat")){
								if(seconds >= (30 * q_num)){
									end_game = true;
									game_check = false;
//									game_timer.cancel();

								}
								points++;
								response.put("extra_payload_info","good job, you got it! Total points = " + Integer.toString(points));
								//Increase turn to the next turn/round of guessing
								turn++;
								System.out.println("(hat success)Turn = " + turn);
								//You won so now the next round's image gets displayed
								internal_count = start_of_pug;
								sendPic(photo_arr[internal_count],response);

								System.out.println("(hat success)Internal_count = " + internal_count);
								more_count = 0;
								System.out.println("(hat success)More_count = " + more_count);
								q_num--;
								System.out.println("(q_num-- " + q_num);
								game_check = true;
							}
						}
						else if(turn == 5){
							if(!user_input.equals("pug")){
								response.put("extra_payload_info", "Try Again!, if you cannot figure it out enter:\"more\" --> 3");
							}
							else if(user_input.equals("pug")){
								if(seconds >= (30 * q_num)){
									end_game = true;
									game_check = false;
//									game_timer.cancel();
								}
								points++;
								response.put("extra_payload_info","good job, you got it! Total points = " + Integer.toString(points));
								//Increase turn to the next turn/round of guessing
								turn++;
								System.out.println("(pug success)Turn = " + turn);
								//You won so now the next round's image gets displayed
								internal_count = start_of_puppy;
								sendPic(photo_arr[internal_count],response);

								System.out.println("(pug success)Internal_count = " + internal_count);
								more_count = 0;
								System.out.println("(pug success)More_count = " + more_count);
								q_num--;
								System.out.println("(q_num-- " + q_num);
								game_check = true;
							}
						}
						//win case img/win.jpg
						else if(turn == 6){
							if(seconds >= (30 * q_num)){
								end_game = true;
								game_check = false;
//									game_timer.cancel();
							}
							response.put("extra_payload_info","SUCCESS YOU MADE IT TO 6!!! Total points = " + Integer.toString(points));
							//sendPic("img/win.jpg",response);
//							game_timer.cancel();
							//end_game = false;
							q_num--;
							game_check = true;
						}

					}


					//lose condition
					if(end_game == true){
						//loop for new game
						response.put("new_game", "new");
						//send loase image
						sendPic("img/lose.jpg",response);
						//let the user know their options
						response.put("extra_payload_info","GAME OVER - > YOU HAVE RUN OUT OF TIME" + "Play again? just enter your name again!");
						//reset img_array location
						internal_count = 0;
						//cancel current time for next game
						game_timer.cancel();
						//set turns back to zero
						turn = 0;
						//set seconds back to zero
						seconds = 0;
						//set points back to zero
						points = 0;
						//resent game win condition back to normal
						end_game = false;

					}
					//win condition
					else if(q_num == 0 && end_game == false ){
						response.put("new_game", "new");
						internal_count = 0;
						turn = 0;
						response.put("extra_payload_info", "You win! " + "Play again? just enter your name again " + "Total score = " + points);
						sendPic("img/win.jpg",response);
						game_timer.cancel();
						seconds = 0;
						points = 0;
					}

//					q_num = 7;
//					end_game = false;


					//==========================================================================
					//will only trigger to signal we have have moved past the entry of the game
					if(turn == 0){
						turn++;
					}
					//==========================================================================

				}
	//==================================================================================================================

	//==================================================================================================================
	//==================================================================================================================
	//==================================================================================================================


				else {
					System.out.println("not sure what you meant");
					response.put("type","error" );
					response.put("message","unknown response" );
				}
				PrintWriter outWrite = new PrintWriter(sock.getOutputStream(), true); // using a PrintWriter here, you could also use and ObjectOutputStream or anything you fancy
				outWrite.println(response.toString());
			}
			
		} catch(Exception e) {e.printStackTrace();}
	}

	/* TODO this is for you to implement, I just put a place holder here */
	public static void sendPic(String filename, JSONObject obj) throws Exception {
		File file = new File(filename);

		if (file.exists()) {
			// import image
			// I did not use the Advanced Custom protocol
			// I read in the image and translated it into basically into a string and send it back to the client where I then decoded again
			obj.put("payload", filename);
		} 
		//return obj;
	}
}
