import java.util.*;
import java.lang.*;
import java.io.*;
class Amr10g
{
    public static void main(String args[])throws Exception
    {
        //Scanner s=new Scanner(System.in);
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(b.readLine());
        int n,k,i;
        long h[],min;
        String temp[]=new String[20000];
        while(t-->0)
        {
            temp=b.readLine().split(" ");
            n=Integer.parseInt(temp[0]);
            k=Integer.parseInt(temp[1]);
            h=new long[n];
            temp=b.readLine().split(" ");
            for(i=0;i<n;i++)
                h[i]=Long.parseLong(temp[i]);
            Arrays.sort(h);
            min=h[k-1]-h[0];
            for(i=1;i<=n-k;i++)
                if(h[i+k-1]-h[i]<min)
                    min=h[i+k-1]-h[i];
            System.out.println(min);
        }
    }
}