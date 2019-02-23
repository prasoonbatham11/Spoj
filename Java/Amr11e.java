import java.util.*;
import java.io.*;

class Amr11e
{
    public static void main(String args[])
    {
        int t,n;
        Scanner sc=new Scanner(System.in);
        t=sc.nextInt();
        int p=2;
        int j;
        boolean seive[]=new boolean[10000];
        Arrays.fill(seive,true);
        seive[0]=false;
        seive[1]=false;
        while(p*p<=3000)
        {
            for(j=2*p;j<=3000;j+=p)
            {
                seive[j]=false;
            }
            for(j=p+1;j<=3000;j++)
                if(seive[j]) {p=j;break;}
        }
        int b[]=new int[3000];
        int i;
        int k=0,c;
        for(i=0;i<3000;i++)
        {
            if(seive[i])
                b[k++]=i;
        }
        int s=k;
        k=0;
        int ans[]=new int[100000];
        for(i=30;i<3000;i++)
        {
            c=0;
            for(j=0;j<s;j++)
            {
                if(c==3)
                {
                    ans[k++]=i;
                    break;
                }
                if(i%b[j]==0)
                {
                    c++;
                }
            }

        }

        while(t-->0)
        {
            n=sc.nextInt();
            System.out.println(ans[n-1]);
        }
    }
}