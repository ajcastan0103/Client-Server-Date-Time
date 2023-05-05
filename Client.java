import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.net.UnknownHostException;

public class Client {
  // components for GUI
  Frame f;
  TextField input;
  TextField result;
  Button b;
  Label h;

  // Class Client
  public Client() {

    /* Create attempt to connect to server with execeptions. 
    Method will attempt to create socket and connect to server 
    and then create GUI with Socket, dis, and dos as parameters.*/
    try {
      Socket server = new Socket("localhost", 3000);

      try {
        DataInputStream dis = new DataInputStream(server.getInputStream());
        DataOutputStream dos = new DataOutputStream(server.getOutputStream());

        /* try to create GUI and connect to server with variables
        server, data input stream and data output stream */
        connect_server(server, dis, dos);
      } catch (Exception exception) {
        System.out.println("Exception occurred in class CLASS method METHOD");
        exception.printStackTrace(); // this gives you more detailed info about the exception
      }

      
    } catch (Exception exception) {
      System.out.println("Exception occurred in class CLASS method METHOD");
      exception.printStackTrace(); // this gives you more detailed info about the exception
    }
    
  }

  // Method to create GUI and communicate with network socket.
  public void connect_server(Socket server, DataInputStream dis, DataOutputStream dos) {
    
    // create interactive and non- iteractive parts of GUI
    f = new Frame("Date and Time");
    f.setLayout(new FlowLayout());
    h = new Label("Enter 'Date' or 'Time' into the text field and click 'Submit'");
    //Text field to enter request
    input = new TextField(50);
    // Button to submit request
    b = new Button("Submit");
    // Text field to store result of request and ouput it to the user on GUI.
    result = new TextField(50);

    // Send request which was submitted from button "b" and text field "input".
    b.addActionListener(new ActionListener() {
      @Override
      //Method to perform action from button
      public void actionPerformed(ActionEvent e) {
        String in = input.getText();

        // Attempt to send request to server with exceptions
        try {
          //write request to server
          dos.writeUTF(in);
          //read the response which was received from server
          String ans = dis.readUTF();
          result.setText(ans);
        } catch (IOException ex) {System.out.print(ex);}

      }
    });

    /* Add components of GUI and set restrictions which 
    user cannot interact/change */
    result.setEditable(false);
    f.setResizable(false);
    f.add(h);
    f.add(input);
    f.add(b);
    f.add(result);
    f.setSize(480, 200);

    // Create window for GUI which user will interact with.
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        System.exit(0);
      }
    });

    f.show();
  }

  // Main method
  public static void main(String[] args) throws UnknownHostException, IOException {
    // Call and run the client in main
    new Client();
  }

}