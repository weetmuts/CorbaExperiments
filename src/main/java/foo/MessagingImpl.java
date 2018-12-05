
package foo;

public class MessagingImpl implements MessagingOperations
{
    public String writeMessage( String s )
    {
        System.out.println("Message: " + s );
        return s + " written";
    }

    public String writeMessages( String[] s )
    {
        for( int i = 0; i < s.length; i++)
        {
            System.out.println("Message: " + s[i] );
        }
        return "string array written";
    }

    public String[] arryfy( String s, int i )
    {
        String result [] = new String[i];
        for( int j = 0; j < i; j++ ) {
            result[j] = s;
        }
        return result;
    }
}
