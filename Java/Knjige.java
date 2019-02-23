import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class Knjige
{
    public static void main(String args[])throws IOException
    {
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(b.readLine()),a[]=new int[n],i,count,p;

        for(i=0;i<n;i++) a[i]=Integer.parseInt(b.readLine());
        count=a[0]-1;p=0;
        for(i=1;i<n;i++)
        {
            if(a[i]>a[p]&&a[i]-a[p]>1)
            {
                count=a[i]-1;p=i;
            }
            else if(a[i]>a[p]&&a[i]-a[p]==1) p=i;
        }
        System.out.println(count);
    }
}