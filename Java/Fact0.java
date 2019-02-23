import java.util.*;
import java.io.*;
class Fact0
{
    public static void main(String args[])throws IOException
    {
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        long n,i;
        int flag,c;
        String x="";
        while(true)
        {
            n=Long.parseLong(b.readLine());
            if(n==0) break;
            flag=0;c=0;
            x="";
            while(n%2==0) {flag=1;n/=2;c++;}
            if(flag==1) x="2^"+c+" ";
            for(i=3;i*i<=n;i+=2)
            {
                flag=0;c=0;
                while(n%i==0)
                {flag=1;n/=i;c++;}
                if(flag==1) x=x+i+"^"+c+" ";
            }
            if(n>1)
            {x=x+n+"^"+1+" ";}
            x=x.trim();
            System.out.println(x);

        }
    }
}
            