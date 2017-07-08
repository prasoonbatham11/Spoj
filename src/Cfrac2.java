import java.io.*;
import java.util.*;
class Cfrac2
{
    public static void main(String args[])throws Exception
    {
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        String[] temp,s;
        int m,n,i,c,j,k;
        long a[]=new long[100000];
        long num,den,t;
        while(true)
        {
            temp=b.readLine().split(" ");
            m=Integer.parseInt(temp[0]);n=Integer.parseInt(temp[1]);
            if(m==n&&n==0) break;
            s=new String[m];
            for(i=0;i<m;i++)
                s[i]=b.readLine();

            k=0;
            for(i=1;i<m;i+=2)
            {
                for(c=0;c<n;c++)
                    if(s[i].charAt(c)>='1'&&s[i].charAt(c)<='9') break;
                for(j=c;j<n;j++)
                    if(s[i].charAt(j)=='.') break;
                a[k]=Long.parseLong(s[i].substring(c,j));
                k++;
            }
            i=m-1;
            for(c=0;c<n;c++)
                if(s[i].charAt(c)>='1'&&s[i].charAt(c)<='9') break;
            a[k]=Long.parseLong(s[i].substring(c,n));
            k++;
            //for(i=0;i<k;i++) System.out.print(a[i]+" ");
            //System.out.println();
            k--;
            num=1;den=a[k];
            k--;
            while(k>=0)
            {
                t=den;
                den=a[k]*den+num;
                num=t;
                k--;
            }
            System.out.println(den+" "+num);
        }
    }
}
            