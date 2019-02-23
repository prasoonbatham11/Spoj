import java.util.*;
import java.lang.*;
import java.io.*;
class Ae00
{
    public static void main(String args[])throws IOException
    {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int i,j,count=0;
        for(i=1;i<=n;i++)
            for(j=i;j<=n;j++)
                if(i*j<=n)
                    count++;
        System.out.println(count);
    }
}