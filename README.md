# Client-Server-Date-Time
A small project simulating a client and server interaction using Java and the Java.awt API. It requests the date or time on the server and displays the received date/date on the client GUI.


Algorithms

Server.java

Server

● Creates a socket and accepts a connection request from the client. Upon successful
connection, the server creates input and output streams to communicate with the client.
getDT

● Accepts a request in the form of a string as an argument.

● If request == “Date”

      ○ Returns date in the form: month/day/year

● If request == “Time”

      ○ Returns time in the form: hour:minute:second

● If request is invalid

      ○ Returns string prompting user to enter valid entry

Client.java

Client

● Creates variables storing a socket, input stream and output stream and sends
connection request to Server. Calls the method “connect_server” with the created
variables passed as arguments.
connect_server

● Creates GUI allowing the user to submit a request for date or time. Writes requests to
server using output stream and reads the result of the date or time from server using
input stream. The returned result is then printed on the GUI.

Integration of Client and Server
Initially, the server is executed which creates a socket and waits for a connection request from
the client. After the connection is made, the client creates a GUI which allows a user to select
either a date or time. The client then sends a string of the request to the server, and the server
returns a string of the result to the client. Finally, the client prints the result on the GUI.

Error handling

Used try and catch blocks to catch common errors which occur when the server and client
communicate with each other.

The following exceptions used are:
● IOException: Catches failed or interrupted I/O operations
● UnknownHostException: Thrown when IP address of host can not be determined
Aside from the try and catch blocks, the “getDT” method in Server.java ensures the client inputs
the correct format when requesting a date or time. If an incorrect format is entered, the user is
prompted to re-enter a valid entry.
