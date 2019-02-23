import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;
class Bishops
{
    public static void main(String args[])throws Exception
    {
        Scanner s=new Scanner(System.in);
        BigInteger b=new BigInteger("0");
        String str="";
        BigInteger two=new BigInteger("2");
        while(s.hasNextBigInteger())
        {
            b=s.nextBigInteger();
            if(b.compareTo(BigInteger.ONE)==0||b.compareTo(BigInteger.ZERO)==0)
                System.out.println(b);
            else
                System.out.println(b.multiply(two).subtract(two));
        }
    }
}