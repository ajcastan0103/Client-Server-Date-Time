import java.net.*;
import java.io.*;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Calendar;

public class Server {

public static void main(String[] args) throws UnknownHostException, IOException {

    try (
            // Create server socket that waits for connection request from client
            ServerSocket server = new ServerSocket(3000);
            // Accepts connection request from client
            Socket connection = server.accept();
            // Create input and output stream
            DataInputStream dis = new DataInputStream(connection.getInputStream());
            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        ) 
    {
            System.out.print("Connection Successful.");
        try
        {
            while (true) 
            {

                // Get requested date or time and store in variable 's'
                String s= getDT(dis.readUTF());
                // Write string s to output stream dos
                dos.writeUTF(s);
            }
        } catch (IOException ie) {System.out.println(ie);}
        
    } catch (UnknownHostException e) {System.out.println(e);}
}
    public static String getDT(String r){
        String result;
        Calendar calendar = Calendar.getInstance();  
        // If date is requested, return date in the form: MONTH-DAY-YEAR
        if (r.toLowerCase().equals("date") |r.toLowerCase().equals("d") )
            {
                result="The Current Date: " + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DATE)+ "/"+
                + calendar.get(Calendar.YEAR); 
            }
            // If time is requested, return time in the form: 
            else if(r.toLowerCase().equals("time")| r.toLowerCase().equals("t"))
            {
                result="The Current Time: " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE)+ ":"+
                + calendar.get(Calendar.SECOND); 
            }
            // If entry is invalid, prompt user to return valid entry
            else{
                 result=("Please enter 'Date' or 'Time' in the text field \nand click 'Submit'.");
            }
        return result;
    }
}