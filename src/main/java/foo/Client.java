package foo;

import foo.bar.Customer;
import foo.bar.v3.*;
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

        Customer c = msg.loadCustomer("123");

        System.out.println("Customer loaded: "+c.id+" "+c.name+" "+c.address);

        orb.shutdown(true);
    }
}
