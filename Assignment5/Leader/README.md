# SER321 Assignment 5 Activity 2
By: Shaun Vulaj

## Description:
This program demonstrates the consenses algorithm as described in ser 321. There is a client which accepts user input. Nodes, which act as banks which have x amount of money reserves.There is a Leader that handles requests from clients and uses those requests from the client to either borrow money through credit, or pay back on the credit that is has taken out.

Notes about running:
-To run leader: gradle runLeader --console=plain -q
-To run node1: gradle runNode1 -Pmoney=1000 --console=plain -q
-To run node2: gradle runNode2 -Pmoney=2000 --console=plain -q
-To run client: gradle runClient --console=plain -q

Notes 2:
There isnt too much error handling in my system. At least for inputs on the client side(due to running out of time) so in order to request credits or pay-backs in the client you much correctly spell them as such,      credit     pay-back.
For them to work.



## A



## B
requirements:
1) readme: ✓
2) ✓ can be a little rough to understand but i think it is easy to follow because most things are right next to each other and follow a particula kind of sequence.
3) ✓
4) ✓
5) ✓
6) ✓
7) ✓
8) ✓
9) ✓
10) ✓, although my system is not persistent(part d is missing that).
11) ✓
12) ✓, data is not persistent though.
13) x
14) x


## C


///////////////////////////////////////////////////////
request:
type: id
data: <String>
-------------------------------------------
response:
OK
type: IDresponse
data: <String>



///////////////////////////////////////////////////////
request:
type: clientID
data: <String>
-------------------------------------------
response:
OK
type: idAccepted
data: <String>
///////////////////////////////////////////////////////


///////////////////////////////////////////////////////
request:
type: choice
data: <String>
-------------------------------------------
response:
OK
type: response
data: <String>
///////////////////////////////////////////////////////


///////////////////////////////////////////////////////
request:
type: creditchoice
data: <String>
-------------------------------------------
response:
OK
type: credit
data: <String>
///////////////////////////////////////////////////////



///////////////////////////////////////////////////////
request:
type: wantcredit
data: <String>
-------------------------------------------
response:
OK
type: yes
data: <String>

response:
OK
type: no
data: <String>
///////////////////////////////////////////////////////

///////////////////////////////////////////////////////
request:
type: paychoice
data: <String>
-------------------------------------------
response:
OK
type: payment
data: <String>
///////////////////////////////////////////////////////


///////////////////////////////////////////////////////
request:
type: wantpay
data: <String>
-------------------------------------------
response:
OK
type: payment
data: <String>

Error
type: error
message: <String> "no money"
///////////////////////////////////////////////////////

request:
type: creditchoice
data: <String>
-------------------------------------------
response:
OK
type: credit
data: <String>
///////////////////////////////////////////////////////











