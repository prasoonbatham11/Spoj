import java.util.*;
import java.io.*;
class Etf
{
    public static void main(String args[])throws IOException
    {
        boolean seive[]=new boolean[32007];Arrays.fill(seive,true);seive[0]=seive[1]=false;
        int prime[]=new int[32000];
        int p=2,q;
        for(p=2;p*p<=32000;p++)
        {
            if(seive[p])
                for(q=p*p;q<=32000;q+=p) seive[q]=false;
            //for(q=p+1;q<=32000;q++) if(seive[q]) p=q;

        }
        //System.out.println(Arrays.toString(seive));
        p=0;
        for(q=2;q<=32000;q++)
            if(seive[q]) prime[p++]=q;
        int k=0;
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(b.readLine());
        int phi,n;
        int j;
        //System.out.println(Arrays.toString(prime));
        while(t-->0)
        {
            n=Integer.parseInt(b.readLine());
            phi=n;
            k=0;
            for(j=prime[k];j*j<=n;j=prime[++k])
            {
                if(n%j==0)
                    phi=(phi/j)*(j-1);
                while(n%j==0) n/=j;

            }
            if(n!=1) phi=(phi/n)*(n-1);
            System.out.println(phi);
        }
    }
}
        