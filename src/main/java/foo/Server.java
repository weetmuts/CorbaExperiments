package foo;

import foo.bar.v3.*;
import java.io.*;
import org.omg.CosNaming.*;

public class Server
{
    public static void main( String[] args ) throws Exception
    {
        if (args.length == 0) {
            System.out.println("java foo.Server [iorfile] {killfile}");
            return;
        }
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

        org.omg.PortableServer.POA poa =
            org.omg.PortableServer.POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

        org.omg.CORBA.Object o =
            poa.servant_to_reference( new MessagingPOATie(new MessagingImpl()) );

        poa.the_POAManager().activate();

        PrintWriter ps = new PrintWriter(new FileOutputStream(new File( args[0] )));
        ps.println(orb.object_to_string(o));
        ps.close();

        if (args.length == 2)
        {
            File killFile = new File(args[1]);
            while(!killFile.exists())
            {
                Thread.sleep(1000);
            }
            orb.shutdown(true);
        }
        else
        {
            orb.run();
        }
    }
}
