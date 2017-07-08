import java.util.*;
import java.io.*;
class Anarc09b
{
    public static void main(String args[])throws IOException
    {
        Scanner s=new Scanner(System.in);
        long a,b,lcm,n;
        while(true)
        {
            a=s.nextLong();b=s.nextLong();
            if(a==0&&b==0)
                return;
            lcm=(a*b)/gcd(a,b);
            n=(lcm/a)*(lcm/b);
            System.out.println(n);
        }
    }
    public static long gcd(long a,long b)
    {
        long t;
        while(b>0)
        {
            t=a%b;
            a=b;
            b=t;
        }
        return a;
    }
}