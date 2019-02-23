import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;
class Nsteps
{
    public static void main(String args[])throws Exception
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        int x,y;
        while(t-->0)
        {
            x=s.nextInt();
            y=s.nextInt();
            if(x==y)
            {
                if(x%2==0)
                    System.out.println(2*x);
                else
                    System.out.println(2*x-1);
            }
            else if(x-y==2)
            {
                if(x%2==0)
                    System.out.println(2*x-2);
                else
                    System.out.println(2*x-3);
            }
            else System.out.println("No Number");
        }
    }
}
