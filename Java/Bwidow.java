import java.util.*;
class Bwidow
{
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt(),n,i,p;long r[]=new long[1000],R[]=new long[1000],max;
        boolean distraction=true;
        while(t-->0)
        {
            distraction=true;
            max=0;
            p=-1;
            n=s.nextInt();
            for(i=0;i<n;i++)
            {
                r[i]=s.nextLong();R[i]=s.nextLong();
                if(r[i]>max)
                {p=i;max=r[i];}
            }
            for(i=0;i<n;i++)
            {
                if(i!=p&&R[i]>=max) distraction=false;
            }
            if(distraction)
                System.out.println(p+1);
            else
                System.out.println(-1);
        }
    }
}
            