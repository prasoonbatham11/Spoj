import java.util.*;
import java.lang.*;
import java.io.*;
class Amr12d
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt(),l,i;
        String p;
        while(t-->0)
        {
            p=s.next();
            l=p.length();
            for(i=0;i<l/2;i++)
            {
                if(p.charAt(i)!=p.charAt(l-i-1))
                {
                    System.out.println("NO");break;
                }
            }
            if(i==l/2)
                System.out.println("YES");
        }
        // your code goes here
    }
}