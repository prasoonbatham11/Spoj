import java.util.*;
import java.lang.*;
import java.io.*;
class Prime1
{
    public static void main(String args[])throws IOException
    {
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(b.readLine());
        long m,n;
        boolean seive[]=new boolean[32000];
        Arrays.fill(seive,true);
        seive[0]=seive[1]=false;
        int p=2,i;
        String temp[]=new String[2];
        while(p*p<32000)
        {
            for(i=2;i*p<32000;i++)
                seive[i*p]=false;
            for(i=p+1;i<32000;i++)
                if(seive[i])
                {p=i;break;}
        }
        long from,j;
        int count;
        while(t-->0)
        {
            count=0;
            temp=b.readLine().split(" ");
            m=Long.parseLong(temp[0]);
            n=Long.parseLong(temp[1]);
            // System.out.println(m+" "+n);
            boolean flag[]=new boolean[(int)(n-m)+1];
            Arrays.fill(flag,true);
            p=2;
            from=2*(m/2);
            while(p*p<=n)
            {
                for(j=from;j<=n;j+=p)
                    if(j>=m&&j!=p) flag[(int)(j-m)]=false;
                for(i=p+1;i<32000;i++)
                    if(seive[i]) {p=i;break;}
                from=p*(m/p);
            }

            for(i=0;i<flag.length;i++)
                if((flag[i]||i+m==2)&&i+m!=1&&i+m!=0) System.out.println(i+m);
            System.out.println();
        }
    }
}