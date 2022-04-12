### Description:
I was not able to fully complete this activity. The best i could get was being able to create multiple peers who can talk to a few of the peers but not all. Will be demonstrated in screencast. Peers can connect to one other peer or maybe multiple peers can talk to one peer but there is an issue trying to get them all to be able to talk to one another.

### How to run it

Arguments are name and port. Start 2 to many peers each having a unique port number. 

1st console: gradle runPeer --args "A 7000" --console=plain -q
2nd console: gradle runPeer --args "B 7001 7000" --console=plain -q
3rd console: gradle runPeer --args "C 7002 7000" --console=plain -q


## ScreenCast:
https://youtu.be/1jvuxcmpDds 

