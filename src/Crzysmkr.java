import java.io.*;
class Crzysmkr
{
    public static int mod(int a,long b,int c)
    {
        int x=1,y=a;
        while(b>0)
        {
            if(b%2==1)
            {
                x=(x*y)%c;
            }
            y=(y*y)%c;
            b/=2;
        }
        return x;
    }
    public static void main(String args[])throws Exception
    {
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(b.readLine());
        long n;
        long m,f;
        while(t-->0)
        {
            n=Long.parseLong(b.readLine());
            f=(mod(34,n,11)+(30%11*n%11)%11+32%11)%11;
            m=11-f;
            if(m==11) m=0;
            System.out.println(m);
        }
    }
}
        