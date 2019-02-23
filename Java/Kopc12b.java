import java.util.*;
import java.io.*;

class Kopc12b
{
    public static void main(String args[])throws IOException
    {
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(b.readLine());
        long fact[]=new long[2000000],m=1000000007;
        fact[0]=1;
        int i,n;
        long x,y,ans;
        for(i=1;i<fact.length;i++) fact[i]=(i*fact[i-1])%m;

        while(t-->0)
        {
            n=Integer.parseInt(b.readLine());
            x=fact[2*n-1];
            y=(fact[n-1]*fact[n-1])%m;
            y=pow2(y,m);
            ans=(x*y)%m;
            System.out.println(ans);
        }
    }
    public static long pow2(long a,long m)
    {
        long x=1,b=m-2,y=a;
        while(b>0)
        {
            if(b%2==1) {x=(x*y)%m;}
            y=(y*y)%m;
            b/=2;
        }
        return x;
    }
}