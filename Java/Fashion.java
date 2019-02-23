import java.util.*;
import java.lang.*;
import java.io.*;
class Fashion
{
    public static void main(String args[])throws IOException
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        int n,m[],w[],sum,i;
        while(t-->0)
        {
            sum=0;
            n=s.nextInt();
            m=new int[n];
            w=new int[n];
            for(i=0;i<n;i++)
                m[i]=s.nextInt();
            for(i=0;i<n;i++)
                w[i]=s.nextInt();
            Arrays.sort(m);
            Arrays.sort(w);
            for(i=0;i<n;i++)
                sum+=m[i]*w[i];
            System.out.println(sum);
        }
    }
}