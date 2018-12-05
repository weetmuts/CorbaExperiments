package foo;

import org.omg.CosNaming.*;
import java.io.*;

public class Client
{
    public static void main(String args[]) throws Exception
    {
        Messaging msg;
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        msg = MessagingHelper.narrow(orb.string_to_object(reader.readLine()));
        System.out.println(msg.writeMessage("Hello World"));
        orb.shutdown(true);
    }
}
