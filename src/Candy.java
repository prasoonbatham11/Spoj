import java.util.*;
import java.lang.*;
import java.io.*;
class Candy
{
    public static void main(String args[])throws IOException
    {
        Scanner s=new Scanner(System.in);
        int n,i,count,sum;
        int a[];
        do
        {
            count=0;sum=0;
            n=s.nextInt();
            if(n==-1)
                break;
            a=new int[n];
            for(i=0;i<n;i++)
            {
                a[i]=s.nextInt();
                sum+=a[i];
            }
            if(sum%n!=0)
            {System.out.println("-1");continue;}
            sum/=n;
            for(i=0;i<n;i++)
                if(a[i]>sum)
                    count+=a[i]-sum;
            System.out.println(count);
        }while(true);
    }
}
            