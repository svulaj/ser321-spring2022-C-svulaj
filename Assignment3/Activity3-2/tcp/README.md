## Task 2 - Preliminary Checklist
# a)
>  This Program is a image guessing game that allows for the functionality to be played over a network or locally 
   depending on the users choice. This game begins by asking the user a name, then how many times they would like to play. Once start is input, a timer begins giving the user 30 seconds for each image.
   After the initial starting data has been entered the user begins to guess what the presented image is. 
   If the user struggle to make a correct guess, they can opt to get a larger image of the object it is diplaying to aid in the guessing of the object. For each "more" option chosen(2 more's = limit) they use more they lose a point). 
   If they are stuck they can opt to input "next" to continue on to the next image. Of course, they will lose another point.
   The game goes on till the number of times the user wishes to play is reached. If they guess all of the images within the alloted time, they win or lose.
   If they wish to play again they can choose to enter the name of the next player and continue.
# b)
> Completed and Tested Requirements: All requirements besides the advanced protocol in #7 for image sending, 
                                     The quit ability in #15, and #16. (15 and 16 were attempted but time has run out)
# c)
> UML is located in assignment three directory in a PDF document titled "UML".
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
# d)
> 
* Description:
 *       start is used to gather the information for the start condition of the system. (given)
 *
 *       name is used to collect the data for the users name.
 *                   "name" is only used here to send the string appropriately.
 *
 *       n_question is used to receive the number of questions/images the user would like to play with.
 *                   "q_num" is the variable used to perform this.(number of questions)
 *
 *       begin is used for the main game.
 *                   "new" is used to focus when a new game is being requested(after they play once)
 *                   "payload" is used for image sending.
 *                   "extra_payload_info" is used for additional information needed to guide the game forwward.
 * 
 * request                             (starting the connection)
 *    type: start
 * Response: 
 * OK
 *  type: hello
 *  image: <String> encoded image
 *  value: <String> asking for name of player
 * Error
 *    type: error
 *  message: <String> Error message
 *
 * ============================================================================================
 *  request                               (collect name)
 *    type: name
 *    name: <String>
 *
 *  Response:
 *  OK
 *  type: name
 *  name: <String> send name
 *  Error
 *  type: error
 *  message: <String> Error message
 * ============================================================================================
 * request                          (how many number of questions?)
 * type: n_question
 * q_num: <String>
 *
 * Response
 * OK
 * type: n_question
 * q_num: <String>
 *
 * Error
 * type: error1                        <----------- Error 1
 * message: <String> Error message
 *
 * String is:
 *     - "You didn't enter in anything" -- Empty string was received
 *     - "must enter a digit" -- User did not send a digit(Integer)
 *     - "Input must be 6 or less" -- User did not send a value that was less than or equal to 6(total amount of turns/pictures)
 *     - "You didnt enter anything in? must enter an integer" -- extra security of ensuring a integer value is passed
 *    - "must enter value larger than 0" -- User did not enter anything/they input a 0.
 * ============================================================================================
 * request                          (Actual start of game)
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
 * type: error2                        <----------- Error 2
 * message: <String> error message
 *
 * String is:
 *     - "enter start when ready to start" -- Lets the user know they have to enter start to begin the game
 *     - "Cannot get any more \"more's\" for this round" -- Lets user know they have extinguished all of their "more" usages for the current turn
 *
 * **NOTE** --> You may see a stack track on the client terminal if you input a strange value,
 *             when you are prompted to enter "start" but the program still works if you continue to input wrong values,
 *             and will work properly as soon as you input "start"
 *
 *
 *
 * @modified-by David Clements <dacleme1@asu.edu> September 2020
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 # e) 






