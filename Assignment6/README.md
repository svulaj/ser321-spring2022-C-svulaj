# GRPC Services and Registry

The following folder contains a Registry.jar which includes a Registering service where Nodes can register to allow clients to find them and use their implemented GRPC services. 

Some more detailed explanations will follow and please also check the build.gradle file

Before starting do a "gradle generateProto".

### gradle runRegistryServer
Will run the Registry node on localhost (arguments are possible see gradle). This node will run and allows nodes to register themselves. 

The Server allows Protobuf, JSON and gRPC. We will only be using gRPC

### gradle runNode
Will run a node with an Echo and Joke service. The node registers itself on the Registry. You can change the host and port the node runs on and this will register accordingly with the Registry

### gradle runClientJava
Will run a client which will call the services from the node, it talks to the node directly not through the registry. At the end the client does some calls to the Registry to pull the services, this will be needed later.

### gradle runDiscovery
Will create a couple of threads with each running a node with services in JSON and Protobuf. This is just an example and not needed for assignment 6. 

### gradle testProtobufRegistration
Registers the protobuf nodes from runDiscovery and do some calls. 

### gradle testJSONRegistration
Registers the json nodes from runDiscovery and do some calls. 

============================================================================================================
SHAUN'S README SECTION:

--> (A)
## Description:
This program is a GRPC Middleware exercise where 2 services were originally available. That being an echo service to echo anything back to the client and a service that tells jokes. 

After getting into the program and understanding how things work I implemented 3 more services, 2 being given to us by the professor and one I originally created. 


The first service I implemented was the TIMER service. This service allows for a user to start a timer, check on a timer, close a timer(turn off), and finally recieve a list of all the timers that have been created. for this service all requirements have been met. The proto has been used to its full extent by using only the objects the proto describes, that being, TimerRequest, TimerResponse, TimerList, and Time.
This service can be seen by using -Pauto=0 for user iteraction and -Pauto=1 for automated execution.

The second service I implemented was the Rock-Paper-Scissors service. This service is kind of self explanatory for anyone who has played rock paper scissors. This service allowed for users to play the game itself and for the users to see a leaderboard of all the users who have played while also displaying those users wins and losses. The proto has been used to its full extent by using only the objects the proto describes, that being, PlayReq, PlayRes, LeaderboardRes, and LeaderboardEntry. This service can be seen by using -Pauto=0 for user iteraction and -Pauto=1 for automated execution.

The third service that was implemented was an original service that I created called Addressbook. This service allows for the creation of an addressbook. By this, I mean that a log of entries is created with particular information about a entry(how to find them and how to contact them). This service only uses the objects defined by the proto, this being, AddressBookResponse, AddressSearchRequest, AddressWriteRequest, Address. 
This service implements the requirements set by the professor, this being, 
-Service allows at least 2 different requests.
-Each request needs at least 1 input.
-Response returns a repeated field.
This service was confirmed by professor Melhase. This service can be seen by using -Pauto=0 for user iteraction and -Pauto=1 for automated execution.

NOTE: All Requirements met.
NOTE: This program does not implement task 3: Building a network together.

---> (B)
## How to run:
To run the node(Server): gradle runNode --console=plain -q
To run the Client: gradle runClient -Pauto=0 --console=plain -q 
NOTE: You can put in -Pauto=0 for user input functionality or you cant put in -Pauto=1 for automated use.

---> (C)
## How to work with the program:
This program takes in only strings and parses integers where they are used. For the Timer service only a name is ever asked of from the user so a simple string is all that is needed to operate the service.

For the Rock-Paper-Scissors service a integer input is all that is asked of the user so a 1,2, or 3 is all that is needed for an input,incorrect inputs are handled here.

For the Addressbook service, all inputs require a string. Name, City, State, Street, and even phone number. Since the phone number is not used for anything specific within the program a string is all that is needed. 


---> (D)

---> (E)

---> (F)
## ScreenCast:
https://youtu.be/Sd20_2R4dFc 













