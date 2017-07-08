import java.util.*;
import java.lang.*;
class Fctrl
{
    public static void main(String args[])throws Exception
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        long n,count;
        while(t-->0)
        {
            count=0;
            n=s.nextLong();
            while(n>0)
            {
                count+=n/5;
                n/=5;
            }
            System.out.println(count);
        }
    }
}