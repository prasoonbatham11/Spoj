import java.io.*;
class Crds
{
    public static void main(String args[])throws IOException
    {
        BufferedReader b= new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(b.readLine());
        long s,n,mod=1000007;
        while(t-->0)
        {
            n=Long.parseLong(b.readLine());
            s=((n*(n+1))%mod+((n*(n-1))/2)%mod)%mod;
            System.out.println(s);
        }
    }
}