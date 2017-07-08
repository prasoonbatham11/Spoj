import java.util.*;import java.io.*;
class Tshow1
{
    public static void main(String args[])throws IOException
    {
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(b.readLine());
        long k,t,i,ten,x;
        String sum;
        while(n-->0)
        {
            k=Long.parseLong(b.readLine());
            t=1;
            i=1;
            x=0;
            while(true)
            {
                //t*=2;
                //i++;
                //x+=t;
                //if(x>k) break;
                if(Math.pow(2,i)-2>=k) break;
                i++;
            }
            //t-=2;
            i--;
            k-=(Math.pow(2,i)-2);
            k--;
            //ten=1;
            sum="";
            while(i-->0)
            {

                if(k%2==0) sum=5+sum;
                else sum=6+sum;
                k/=2;//ten*=10;
            }
            System.out.println(sum);
        }
    }
}