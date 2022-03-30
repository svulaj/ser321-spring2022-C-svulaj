# Assignment 4 Activity 1
## Description
    The initail Performer code only has one function for adding strings to an array: 
    ThreadedServer: can be ran with gradle, there is a specific task that can run it with an example command.
    ThreadPoolServer: allows us to use performer over multiple clients. These clients all communicate with one another and can operate on the same list.

## (b)
    you can run the program by running: gradle runTask1
                                        gradle runTask2
                                        gradle runTask3 to run the server you wish to use.
                                        and you can run as many clients as you like depending on the server you decided to run.
## (c)
    All inputs are Strings. Error handling is used for interger and characters.

## (d) 
    https://youtu.be/woBh8uhtpO8 

## (f)
    all requirements met.
##============================================================================================
# Assignment 4 Activity 2
## (a)
    Description:
            Simple battleship game where clients are able to participate together in the effort of sinking hidden battleship on a board. Multpile users can participate and leaders can be tracked for whom ever wins a game.
## (b)
    you can run the program by running: gradle runClient
                                        gradle runServer

## (c)
    The program only accepts strings. Integers are handled.
    You input one letter and then a number (E.g. b2, on the same line).

## (d)
    https://youtu.be/rq0mzMYgqNY 

## (f)
    Requirements met:
                1 - yes
                2 - yes
                3 - yes
                4 - yes
                5 - (half of it, leader board exists but sometimes is the same for both, usually not)

                6 - (joins running game and can be played together but must be played slowly in order not to break. Users can only play a game together when multiple clients are connected. there is no running game then connection happens and new connection can opt to play their own. all users must play together unforturnately. 
                    I tried my best but i think the way i implemented it prevented me from doing easily so i made life harder on myself and couldnt get it full going.)
                7 - (although sometimes a crash can occur, cannot fully tell why)
                8 - yes
                9 - no
                10 - yes
                11 - yes
                12 - yes
                13 - (half and half board updates when one player makes a move and then the next player makes a move, not immediately after a move is made)
                14 - yes
                15 - yes
                16 - yes
                17 - yes


    

## Protocol

### Requests
request: { "selected": <int: 1=add, 2=remove, 3=display, 4=count, 5=reverse,
0=quit>, "data": <thing to send>}

  add: data <string>
  remove: data <int>
  display: no data
  count: no data
  reverse: data <int>
  quit: no data

### Responses

sucess response: {"type": <"add",
"remove", "display", "count", "reverse", "quit"> "data": <thing to return> }

type <String>: echoes original selected from request (e.g. if request was "add", type will be "add")
data <string>: add = new list, remove = removed element, display = current list, count = num elements, reverse = new list


error response: {"type": "error", "message"": <error string> }
Should give good error message if something goes wrong which explains what went wrong
String is:
    - "Error 1 - not and INTERGER" 
            Indicates that there was an error within the doPerform method within performer.java. Where the value passed into the method for a particular service was not the one permitted.
    - "Error 2 - out of bounds, re-enter a number within bounds"
            Indicates that the user input for a particular service involving the list was outside of the bounds of the current list that the user is trying to operate on.

## How to run the program
### Terminal
Base Code, please use the following commands:
```
    For Server, run "gradle runServer -Pport=9099 -q --console=plain"
```
```   
    For Client, run "gradle runClient -Phost=localhost -Pport=9099 -q --console=plain"
```   



