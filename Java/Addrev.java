import java.util.*;
import java.lang.*;
import java.io.*;
class Addrev
{
    public static void main(String args[])throws IOException
    {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int a,b;
        while(n-->0)
        {
            a=s.nextInt();
            b=s.nextInt();
            System.out.println(reverse(reverse(a)+reverse(b)));
        }
    }
    public static int reverse(int n)
    {
        int r=0;
        while(n>0)
        {
            r=10*r+n%10;
            n/=10;
        }
        return r;
    }
}