import java.util.*;
import java.lang.*;

class Test
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            int n = sc.nextInt();
            if(n!=42) System.out.println(n);
            else break;
        }

    }
}