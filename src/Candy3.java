import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;
class Candy3
{
    public static void main(String args[])throws IOException
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        long n,i;
        long sum;
        long a;
        while(t-->0)
        {
            s.nextLine();
            n=s.nextInt();
            sum=0;
            for(i=0;i<n;i++)
            {a=s.nextLong();sum+=a%n;}
            if(sum%n==0)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}