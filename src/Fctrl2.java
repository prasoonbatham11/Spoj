import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;
class Fctrl2
{
    public static void main(String args[])throws Exception
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        long n,i;
        BigInteger factorial;
        while(t-->0)
        {
            factorial=BigInteger.ONE;
            n=s.nextLong();
            for(i=1;i<=n;i++)
                factorial=factorial.multiply(new BigInteger(i+""));
            System.out.println(factorial);
        }
    }
}
