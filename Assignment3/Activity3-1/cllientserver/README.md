##### Author: Shaun Vulaj, ASU Polytechnic, CIDSE, SE
* Version: February 2022


##### Purpose
Each program has a short description as well as the Gradle file
* Please run `Server` and `Client` together.

##### Protocol Description
This program facilitates the functionality of performing a permutation operation on 2 numbers between 10 - 0
The user enters in 2 integers for permutaions & 1 string for the palindrome option

============================================================================================
client
#### permutation using 2 Integer
###### Request 
    {"type": "perm", "data": <String>, "data2": <String>,"data3": <String>}

Error cases
    {"type": "error", "message": <String>}

    String is:
    - "no String" -- if the "data" field in the request is not a String
    - "Data missing" -- if the request does not have a "data" field
    
server
###### Response
Ok case
    {"type": "perm", "data": <Integer>}
Error cases
    {"type": "error", "message": <String>}

    Integer is:
    - "no Integer" -- if the "data" field in the request is not a Integer
    - "Data missing" -- if the request does not have a "data" field


============================================================================================

#### Checki if a String is a Palindrome
###### Request
    {"type": "pal", "data": <String>}

###### Response
Ok case
    {"type": "pal", "data": <String>}
Error cases: 
   Error cases
    {"type": "error", "message": <String>}

    Integer is:
    - "not a String" -- if the "data" field in the request is not a Integer
    - "Data missing" -- if the request does not have a "data" field
============================================================================================

#### Exit
###### Request
    {"type": "exit"}

###### Response
Ok case
    {"type": "exit", "data": <String>}
Error cases: 
    no error cases implemented, client will likely not receive an answer -- this is of course not good


#### Request type unknown
Server will respond with:
{"type": "error", "message": "Request type not known"}