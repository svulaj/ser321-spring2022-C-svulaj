# Assignment 4 Activity 1
## Description
The initail Performer code only has one function for adding strings to an array: 
ThreadedServer: can be ran with gradle, there is a specific task that can run it with an example command.
ThreadPoolServer:

# Assignment 4 Activity 2
## Description
The initail Performer code only has one function for adding strings to an array: 

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



