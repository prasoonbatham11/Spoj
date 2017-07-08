import java.util.*;
import java.lang.*;
public class Facefrnd
{
    public static void main(String args[])throws Exception
    {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int a[]=new int[n];
        int k=0,i=0,j,m,count=0,p=n;
        int f[]=new int[10000];
        while(n-->0)
        {
            a[i++]=s.nextInt();
            m=s.nextInt();
            for(j=0;j<m;j++)
            {f[k++]=s.nextInt();}
        }
        Arrays.sort(f,0,k);
        Arrays.sort(a);
        if(Arrays.binarySearch(a,f[0])<0)
            count++;
        for(i=1;i<k;i++)
        {
            if(Arrays.binarySearch(a,f[i])<0&&f[i]!=f[i-1])
                count++;
        }
        System.out.println(count);
    }
}