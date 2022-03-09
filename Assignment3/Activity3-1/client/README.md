##### Author: Tommy Nguyen
* Version: March 2022


##### Purpose
Each program has a short description as well as the Gradle file
* Please run `Server` and `Client` together.

##### Protocol Description

--------------------------------------------------------------------------------------------------------------
#### Count Vowels
#### Description
	Counts the number of vowels within a given string. The algorithim loops through each character and 
	checks for which one is a vowel. If a vowel is found, a vowel counter will increment.

###### Request
	{"type": "vowels", "data": <String>}
	
###### Response
Ok case
	{"type": "vowels", "data": <String>}
Error cases:
	{"type": "error", "message": <String>}
	
	String is:
    - "no String" -- if the "data" field in the request is not a String
    - "Data missing" -- if the request does not have a "data" field
--------------------------------------------------------------------------------------------------------------
#### Multiplicity Check
#### Description
	Checks if the 2nd given number is a multiplicity of the 1st given number. If the 2nd given number turned out
	to be a multiplicity, the client will print what the number is multiplied by to make up the first number.
###### Request
	{"type": "multiplicity", "data1": <String>, "data2": <String>}
	
###### Response
Ok case
	{"type": "multiplicity", "data": <String>}
Error cases:
	{"type": "error", "message": <String>}
	
	String is:
	- "No Integers found" -- if a user inputed something that is not an integer.
	- "Data missing" -- one or both Integers are not present for data1 and or data2.
--------------------------------------------------------------------------------------------------------------

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